package com.temp.util;

import cn.hutool.json.JSONUtil;
import com.temp.domain.dto.email.MailConfigDTO;
import com.temp.domain.dto.email.MailInfoDTO;
import com.temp.domain.dto.email.MailInfoFileDTO;
import com.temp.exception.BusinessException;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 发送电子邮件工具类
 *
 * @author Hollis
 * @since 2024-03-10 16:37
 */
@Log4j2
public class EmailUtil {

    private static final ConcurrentHashMap<String, Session> SESSION_MAP = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Transport> TRANSPORT_MAP = new ConcurrentHashMap<>();

    /**
     * 发送邮件 - No CC
     *
     * @param emailSubject   邮件主题
     * @param emailContent   邮件正文
     * @param filePaths      附件地址 List (注: 本地文件绝对路径)
     * @param emailToAddress 收件人邮箱地址
     * @param mailConfig     发件人信息[发件人邮箱, 邮箱密码, 发送邮箱服务器地址, 发送邮箱服务器端口]
     */
    public static void sendMail(String emailSubject,
                                String emailContent,
                                List<String> filePaths,
                                List<String> emailToAddress,
                                MailConfigDTO mailConfig) throws Exception {
        sendMailAndCc(emailSubject, emailContent, filePaths, emailToAddress, Collections.emptyList(), mailConfig);
    }

    /**
     * 发送邮件 - CC 版本
     *
     * @param emailSubject   邮件主题
     * @param emailContent   邮件正文
     * @param filePaths      附件地址 List (注: 本地文件绝对路径)
     * @param emailToAddress 收件人邮箱地址
     * @param emailCcAddress 抄送人邮箱地址
     * @param mailConfig     发件人信息[发件人邮箱, 邮箱密码, 发送邮箱服务器地址, 发送邮箱服务器端口]
     */
    public static void sendMailAndCc(String emailSubject,
                                     String emailContent,
                                     List<String> filePaths,
                                     List<String> emailToAddress,
                                     List<String> emailCcAddress,
                                     MailConfigDTO mailConfig) throws Exception {
        if (CollectionUtils.isEmpty(emailToAddress)) {
            throw new BusinessException("收件人邮箱地址不能为空");
        }

        MailInfoDTO mailInfo = new MailInfoDTO();
        mailInfo.setEmailToAddress(emailToAddress);
        mailInfo.setEmailCcAddress(emailCcAddress);
        mailInfo.setEmailSubject(emailSubject);
        mailInfo.setEmailContent(emailContent);

        List<MailInfoFileDTO> mailInfoFiles = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(filePaths)) {
            for (String fileUrl : filePaths) {
                MailInfoFileDTO mailInfoFile = new MailInfoFileDTO();
                mailInfoFile.setFileUrl(fileUrl);
                mailInfoFiles.add(mailInfoFile);
            }
        }

        try {
            sendMail(mailInfo, mailInfoFiles, mailConfig);
        } catch (Exception e) {
            log.error("发送邮件失败：{}", mailInfo, e);
            throw new Exception(e);
        }
    }

    /**
     * 发送邮件 - 底层发送
     *
     * @param mailInfo       邮件内容
     * @param mailInfoFiles  邮件附件
     * @param mailConfigInfo 邮箱配置
     */
    public static void sendMail(MailInfoDTO mailInfo,
                                List<MailInfoFileDTO> mailInfoFiles,
                                MailConfigDTO mailConfigInfo) throws Exception {
        log.info("EmailUtil#sendMail mailInfo: {}, mailInfoFiles: {}, mailConfigInfo:{}",
                JSONUtil.toJsonStr(mailInfo),
                JSONUtil.toJsonStr(mailInfoFiles),
                JSONUtil.toJsonStr(mailConfigInfo));
        Session session = SESSION_MAP.get(mailConfigInfo.getEmailAddress());
        @Cleanup Transport transport = TRANSPORT_MAP.get(mailConfigInfo.getEmailAddress());
        if (session == null || transport == null || !transport.isConnected()) {
            //1、连接邮件服务器的参数配置
            Properties props = new Properties();
            //设置用户的认证方式
            props.setProperty("mail.smtp.auth", "true");
            //设置传输协议
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.mime.splitlongparameters", "false");
            System.setProperty("mail.mime.splitlongparameters", "false");
            //设计端口
            props.put("mail.smtp.port", mailConfigInfo.getSmtpPort());
            props.put("mail.smtp.socketFactory.port", mailConfigInfo.getSmtpPort());
            //设置发件人的SMTP服务器地址
            props.setProperty("mail.host", mailConfigInfo.getSmtpAddress());
            //2、创建定义整个应用程序所需的环境信息的 Session 对象
            session = Session.getInstance(props);
            log.info("获取邮件session：邮件主题：{}", mailInfo.getEmailSubject());
            //设置调试信息在控制台打印出来
            //session.setDebug(true);

            transport = session.getTransport();
            //设置发件人的账户名和密码
            log.info("连接邮件服务器：邮件主题：{}", mailInfo.getEmailSubject());
            transport.connect(mailConfigInfo.getSmtpAddress(), mailConfigInfo.getEmailAddress(), mailConfigInfo.getEmailPassword());
            SESSION_MAP.put(mailConfigInfo.getEmailAddress(), session);
            TRANSPORT_MAP.put(mailConfigInfo.getEmailAddress(), transport);
        } else {
            log.info("从本地缓存中获取邮件连接：{}", mailConfigInfo.getEmailAddress());
        }
        //3、创建邮件的实例对象
        Message msg = getMimeMessage(session, mailConfigInfo.getEmailAddress(), mailInfo, mailInfoFiles);
        //4、根据session对象获取邮件传输对象Transport
        log.info("开始发送邮件到服务器：邮件主题：{}", mailInfo.getEmailSubject());
        //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(msg, msg.getAllRecipients());
        log.info("结束发送邮件到服务器：邮件主题：{}", mailInfo.getEmailSubject());
    }

    /**
     * 获得创建一封邮件的实例对象,发送邮件
     */
    private static MimeMessage getMimeMessage(Session session,
                                              String emailAddress,
                                              MailInfoDTO mailInfo,
                                              List<MailInfoFileDTO> mailInfoFiles) throws Exception {
        // 创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);

        // 设置发件人地址
        msg.setFrom(new InternetAddress(emailAddress));

        // 收件人
        List<String> emailToAddress = mailInfo.getEmailToAddress();
        Address[] addresses = new Address[emailToAddress.size()];
        for (int i = 0; i < emailToAddress.size(); i++) {
            addresses[i] = new InternetAddress(emailToAddress.get(i));
        }
        msg.setRecipients(MimeMessage.RecipientType.TO, addresses);

        // 抄送人
        if (CollectionUtils.isNotEmpty(mailInfo.getEmailCcAddress())) {
            List<String> emailCcAddress = mailInfo.getEmailCcAddress();
            Address[] addressesCc = new Address[emailCcAddress.size()];
            for (int i = 0; i < emailCcAddress.size(); i++) {
                if (StringUtils.isNotBlank(emailCcAddress.get(i))) {
                    addressesCc[i] = new InternetAddress(emailCcAddress.get(i));
                }
            }
            msg.setRecipients(MimeMessage.RecipientType.CC, addressesCc);
        }

        // 密送人
        if (CollectionUtils.isNotEmpty(mailInfo.getEmailBccAddress())) {
            List<String> emailBccAddress = mailInfo.getEmailBccAddress();
            Address[] addressesBcc = new Address[emailBccAddress.size()];
            for (int i = 0; i < emailBccAddress.size(); i++) {
                if (StringUtils.isNotBlank(emailBccAddress.get(i))) {
                    addressesBcc[i] = new InternetAddress(emailBccAddress.get(i));
                }
            }
            msg.setRecipients(MimeMessage.RecipientType.BCC, addressesBcc);
        }

        //4.设置邮件主题
        msg.setSubject(mailInfo.getEmailSubject(), "UTF-8");

        // 6. 创建文本"节点"
        MimeBodyPart text = new MimeBodyPart();
        // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent(mailInfo.getEmailContent(), "text/html;charset=UTF-8");

        // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        if (CollectionUtils.isNotEmpty(mailInfoFiles)) {
            List<MailInfoFileDTO> mailInfoFileList = mailInfoFiles.stream()
                    .filter(p -> StringUtils.isNotBlank(p.getFileUrl()))
                    .toList();
            for (MailInfoFileDTO mailFile : mailInfoFileList) {
                MimeBodyPart attachment = new MimeBodyPart();
                // 读取URL地址
                //URL url = new URL(mailFile.getFileUrl());
                DataSource dataSource = new FileDataSource(mailFile.getFileUrl());
                DataHandler dh2 = new DataHandler(dataSource);
                // 将附件数据添加到"节点"
                attachment.setDataHandler(dh2);
                if (StringUtils.isBlank(mailFile.getFileName())) {
                    mailFile.setFileName(mailFile.getFileUrl().contains("/")
                            ? mailFile.getFileUrl().substring(mailFile.getFileUrl().lastIndexOf("/") + 1)
                            : mailFile.getFileUrl().substring(mailFile.getFileUrl().lastIndexOf("\\") + 1));
                }
                mailFile.setFileName(mailFile.getFileName()
                        .replaceAll("\r", "")
                        .replaceAll("\n", ""));
                // 设置附件的文件名（需要编码）
                attachment.setFileName(MimeUtility.encodeText(mailFile.getFileName()));
                // 如果有多个附件，可以创建多个多次添加
                mm.addBodyPart(attachment);
            }
        }
        // 混合关系
        mm.setSubType("mixed");
        // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        msg.setContent(mm);
        //设置邮件的发送时间,默认立即发送
        //msg.setSentDate(new Date());
        return msg;
    }

    // ------------------------------------------- 通用检查 --------------------------------------------

    /**
     * 检查邮箱是否是电子邮箱格式
     *
     * @param emailAddress 邮箱地址
     * @return true/false
     */
    public static boolean check(String emailAddress) {
        // 根据RFC 5322规范定义的电子邮件正则表达式
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(emailAddress).matches();
    }
}
