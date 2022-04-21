import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;



public class Principal extends JFrame{
    private JButton limpiarButton;
    private JPanel panel1;
    private JButton entrarButton;
    private JTextField textField1;
    private JTextField textField2;
    FileOutputStream fos = null;
    ObjectOutputStream salida = null;

    public Principal(){
        setContentPane(panel1);
        setBounds(450,300,500,270);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel Montealegre**");

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                textField2.setText(null);
            }
        });
        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Usuario="";
                String contraseña="";
                if(textField1.getText().equals(Usuario) && textField2.getText().equals(contraseña)) {
                    entrarButton.setSelected(true);
                    Entrada myIntro = null;
                    try {
                        myIntro = new Entrada();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    myIntro.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Contraseña y/o usuario no valido","ERROR",JOptionPane.ERROR_MESSAGE);
                    textField1.setText(null);
                    textField2.setText(null);
                }
            }
        });
    }

    public static void main(String[] args) {
        Principal myFrame=new Principal();
    }
}
