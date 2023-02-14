package com.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String to = request.getParameter("email");
		String OTP = request.getParameter("OTP");
		String account = request.getParameter("account");
		String amount = request.getParameter("amount");
		System.out.println(to);
		System.out.println(OTP);
		response.sendRedirect("http://localhost:8082/SSE4354MerchantSystem/CheckOTP.jsp?account="+account+"&amount="+amount);

		SendEmail(to, OTP);
//		SendEmail("wankang2019@gmail.com", "123345");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void SendEmail(String email, String OTP) {
		String to = email;
//        System.out.println(to);
//        System.out.println(OTP);

        // Sender's email ID needs to be mentioned
//        String from = "wankang2019@sina.com";
        String from = "wankang2019@gmail.com";

        // Assuming you are sending email from through gmails smtp
//        String host = "smtp.sina.com";
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("wankang2019@gmail.com", "GmailAuthentication");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Your One Time Password!");

            // Now set the actual message
            message.setText("This is your OTP: " + OTP + ". The OTP will expire after five minutes");

//            System.out.println("sending...");
            // Send message
            Transport.send(message);
//            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}

}
