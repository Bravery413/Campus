package com.gdufe.campus.utils;

import com.gdufe.campus.config.EmailConfig;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.pojo.DTO.UserDTO;
import com.gdufe.campus.pojo.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author bravery
 * @date 2019/8/19 16:13
 */
@Slf4j
public class EmailUtil {
    private static EmailConfig emailConfig;
    public static void sendEmail(UserDTO userDTO) {
        try {
            String name=userDTO.getUsername();
            String account=userDTO.getAccount();
            String key=userDTO.getActive();

            String emailAdd=userDTO.getEmail();
            Date now = new Date( );
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sdf.format(now);
            String url="http://192.168.10.83/user/active?account="+account+"&key="+key;
            System.out.println(url);

            HtmlEmail email = new HtmlEmail();//不用更改
            email.setHostName(emailConfig.getHostName());//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailAdd);// 收件地址

            email.setFrom(emailConfig.getSendFromEmailAddress(), emailConfig.getSendFromName());//此处填邮箱地址和用户名,用户名可以任意填写

            email.setAuthentication(emailConfig.getSendFromEmailAddress(), emailConfig.getEmailPassword());//此处填写邮箱地址和客户端授权码

            String msg="<div style=\"background-color:#ECECEC; padding: 35px;\">\n" +
                    "    <table cellpadding=\"0\" align=\"center\"\n" +
                    "           style=\"width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;\">\n" +
                    "        <tbody>\n" +
                    "        <tr>\n" +
                    "            <th valign=\"middle\"\n" +
                    "                style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #42a3d3; background-color: #49bcff; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;\">\n" +
                    "                <font face=\"微软雅黑\" size=\"5\" style=\"color: rgb(255, 255, 255); \">注册成功,请尽快激活! （广财周边App）</font>\n" +
                    "            </th>\n" +
                    "        </tr>\n" +
                    "        <tr>\n" +
                    "            <td>\n" +
                    "                <div style=\"padding:25px 35px 40px; background-color:#fff;\">\n" +
                    "                    <h2 style=\"margin: 5px 0px; \">\n" +
                    "                        <font color=\"#333333\" style=\"line-height: 20px; \">\n" +
                    "                            <font style=\"line-height: 22px; \" size=\"4\">\n" +
                    "                                亲爱的 "+name+"</font>\n" +
                    "                        </font>\n" +
                    "                    </h2>\n" +
                    "                    <p>首先感谢您加入本站!<br>\n" +
                    "                        您的账号：<b>"+account+"</b><br>\n" +
                    "                        <a href='"+url+"'>点击激活账号:</a><br>\n" +
                    "                        如果无法点击,请复制以下地址到浏览器访问激活:<br>\n" +
                    "                        "+url+"<br><br>\n" +
                    "                       \n" +
                    "                    <p align=\"right\">Breavry</p>\n" +
                    "                    <p align=\"right\">"+date+"</p>\n" +
                    "                    <div style=\"width:700px;margin:0 auto;\">\n" +
                    "                        <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\n" +
                    "                            <p>此为系统邮件，请勿回复</p>\n" +
                    "                            当您在使用本网站时，请遵守当地法律法规。<br>\n" +
                    "                            如果您有什么疑问可以联系管理员，Email: 2472937751@qq.com</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </div>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "        </tbody>\n" +
                    "    </table>\n" +
                    "</div>";
            email.setSubject(emailConfig.getEmailTitle());//此处填写邮件名，邮件名可任意填写
            email.setMsg(msg);//此处填写邮件内容
            email.send();
        } catch (Exception e) {
            log.error("[邮箱激活] 邮箱激活失败");
        }
    }
}
