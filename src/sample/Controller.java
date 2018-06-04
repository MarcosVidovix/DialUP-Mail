package sample;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.apache.commons.mail.EmailException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private int qtdanexo = 0;
    private int qtddest = 0;
    private List<String> destinatarios = new ArrayList<>();
    public static List<String> anexo = new ArrayList<>();

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_destinatario;

    @FXML
    private JFXPasswordField txt_senha;

    @FXML
    private JFXTextArea txt_msg;

    @FXML
    private JFXButton btn_anexo;

    @FXML
    private JFXButton btn_enviar;

    @FXML
    private JFXTextField txt_assunto;

    @FXML
    private Text msg_enviado;

    @FXML
    private JFXTextField txt_url;

    @FXML
    private Text txt_anexados;

    @FXML
    private Text txt_qtddestinatarios;

    @FXML
    private JFXButton btn_incluir;

    @FXML
    void incluir(MouseEvent event) {
        qtddest++;
        destinatarios.add(txt_destinatario.getText());
        txt_destinatario.setText(null);
        txt_qtddestinatarios.setText(String.valueOf(qtddest));
    }

    @FXML
    void anexo(MouseEvent event) {
        qtdanexo++;
        anexo.add(txt_url.getText());
        txt_url.setDisable(true);
        txt_anexados.setText(String.valueOf(qtdanexo));
        for (int i = 0; i <= 100000000; i++) {
        }
        txt_url.setDisable(false);
        txt_url.setText(null);

    }

    @FXML
    void cancela(MouseEvent event) {
        txt_email.setText(null);
        txt_senha.setText(null);
        txt_assunto.setText(null);
        txt_msg.setText(null);
        txt_destinatario.setText(null);
        msg_enviado.setText(null);
        txt_url.setText(null);
        txt_anexados.setText(null);
        txt_url.setDisable(false);
        txt_email.setDisable(false);
        txt_senha.setDisable(false);
        qtdanexo = 0;
        qtddest = 0;
        destinatarios.clear();
        anexo.clear();
        txt_qtddestinatarios.setText(null);
    }

    @FXML
    void enviar(MouseEvent event) throws EmailException {
        checarEmail();
        checarSenha();
        enviarMsg();
        msg_enviado.setText("Enviado!");
    }

    public void checarEmail() {
        String email = txt_email.getText();

        if (email.contains("@gmail.com")) {
            txt_email.setVisible(true);
            txt_email.setDisable(true);

        } else if (!email.contains("@gmail.com") && (email.length() > 100)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();

        }
        validarEnvio();
    }

    public void checarSenha() {

        String senha = txt_senha.getText();

        if (senha.length() > 6) {
            txt_senha.setVisible(true);
            txt_senha.setManaged(false);
            txt_senha.setDisable(true);
        }

        validarEnvio();
    }

    void validarEnvio() {
        btn_enviar.setVisible(true);
    }

    public void enviarMsg() throws EmailException {
        for (int i = 0; i < destinatarios.size(); i++) {
            EnviarEmail enviar = new EnviarEmail(txt_email.getText(), txt_senha.getText(), destinatarios.get(i), txt_assunto.getText(), txt_msg.getText(), (ArrayList<String>) anexo);
            if (txt_url.isDisable()) {
                enviar.emailAnexo();
            } else {
                enviar.emailSimples();

            }
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}




