package sample;

import org.apache.commons.mail.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EnviarEmail {

    private String smtp = "smtp.googlemail.com";
    private int port = 465;
    private String remetent;
    private String senha;
    private String assunto;
    private String destinatario;
    private String msg;
    private List<String> anexos = new ArrayList<>();

    public EnviarEmail(String rmt, String senha, String dest, String assunto, String msg, ArrayList<String> anexo) {

        this.remetent = rmt;
        this.senha = senha;
        this.destinatario = dest;
        this.assunto = assunto;
        this.msg = msg;
        for (int i = 0; i < anexo.size(); i++){
            anexos.add(anexo.get(i));
        }
    }

    void emailSimples() throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(this.smtp);
        email.setSmtpPort(this.port);
        email.setAuthenticator(new DefaultAuthenticator(this.remetent, this.senha));
        email.setSSLOnConnect(true);
        email.setFrom(this.remetent);
        email.setSubject(this.assunto);
        email.setMsg(this.msg);
        email.addTo(this.destinatario);
        email.send();
    }

    void emailAnexo() throws EmailException {

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(this.smtp);
        email.setSmtpPort(this.port);
        email.setAuthenticator(new DefaultAuthenticator(this.remetent, this.senha));
        email.setSSLOnConnect(true);
        email.setFrom(this.remetent);
        email.setSubject(this.assunto);
        email.setMsg(this.msg);
        email.addTo(this.destinatario);

        for (int i = 0; i < Controller.anexo.size(); i++) {
            EmailAttachment anexo = new EmailAttachment();
            File arquivo = new File(Controller.anexo.get(i));
            anexo.setPath(arquivo.getPath());
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setDescription("Arquivo Anexado");
            anexo.setName("Anexo");
            email.attach(anexo);
            email.send();
        }


    }
}
