import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import pC05.*;

public class Entrada extends JFrame implements Serializable {
    private JButton dni;
    private JPanel ventanaEmergente;
    private JTextField noReservas;



    private JPanel panel1;
    private JButton cancelarButton;
    private JButton limpiarButton;
    private JButton reservarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField7;
    private JTextField textField8;
    private JButton calcularButton;
    private JRadioButton estandarRadioButton;
    private JRadioButton suiteRadioButton;
    private JRadioButton balcónRadioButton;
    private JComboBox comboBox1;
    private JTextField a0TextField;
    private JTextField a0TextField1;
    private JTextField a0TextField2;
    private JLabel precioFinal;
    private JTextField textField6;
    private JTextArea textArea1;
    private JButton anularButton;

    private static Hotel h;



    public Entrada() throws IOException,ClassNotFoundException {
        h=new Hotel();

        FileInputStream fis = new FileInputStream("HotelSerializar.dat");
        ObjectInputStream entrada = new ObjectInputStream(fis);

        comboBox1.setSelectedItem(null);
        setContentPane(panel1);
        setBounds(225,150,1000,600);
        setVisible(false);
        setTitle("Reservas");
        h = (Hotel) entrada.readObject();
        textArea1.setText(h.toString());

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        estandarRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(estandarRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Seleccionaste habitación estandar.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }else JOptionPane.showMessageDialog(null, "Deseleccionaste habitación estandar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        balcónRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(balcónRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Seleccionaste habitación con balcón.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }else JOptionPane.showMessageDialog(null, "Deseleccionaste habitación con balcón.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        suiteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(suiteRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Seleccionaste habitación Suite.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }else JOptionPane.showMessageDialog(null, "Deseleccionaste habitación Suite.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textField5.setText("");
                textField6.setText("");
                textField7.setText("");
                textField8.setText("");
                a0TextField.setText("0");
                a0TextField1.setText("0");
                a0TextField2.setText("0");
                precioFinal.setText("0€");
                comboBox1.setSelectedItem(null);
                estandarRadioButton.setSelected(false);
                balcónRadioButton.setSelected(false);
                suiteRadioButton.setSelected(false);
            }
        });
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int precio=0;
                if(estandarRadioButton.isSelected()){
                    int numeroEntero1=Integer.parseInt(a0TextField.getText());
                    for(int i=0;i<numeroEntero1;i++){
                        precio+=150;
                    }
                }
                if(balcónRadioButton.isSelected()){
                    int numeroEntero2=Integer.parseInt(a0TextField1.getText());
                    for(int i=0;i<numeroEntero2;i++){
                        precio+=200;
                    }
                }
                if(suiteRadioButton.isSelected()){
                    int numeroEntero3=Integer.parseInt(a0TextField2.getText());
                    for(int i=0;i<numeroEntero3;i++){
                        precio+=250;
                    }
                }
                if(comboBox1.getSelectedItem()=="Sin desayuno"){
                    precio=precio*1;
                }
                if(comboBox1.getSelectedItem()=="Con desayuno"){
                    precio=precio*2;
                }
                if(comboBox1.getSelectedItem()=="Media Pensión"){
                    precio=precio*3;
                }
                if(comboBox1.getSelectedItem()=="Pensión Completa"){
                    precio=precio*4;
                }
                String s=String.valueOf(precio);
                precioFinal.setText(s+"€");

            }
        });
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                try{
                    fos = new FileOutputStream("HotelSerializar.dat");
                }catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }
                ObjectOutputStream salida = null;
                try{
                    salida = new ObjectOutputStream(fos);
                }catch(IOException ex){
                    ex.printStackTrace();
                }

                if(precioFinal.getText().equals("0€") || textField7.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"No se puede reservar. Rellene todos los campos.","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Cliente c = new Cliente();
                    c.setNombre(textField1.getText());
                    c.setApellido(textField2.getText());
                    c.setTelefono(Integer.parseInt(textField4.getText()));
                    c.setDNI(Integer.parseInt(textField7.getText()));
                    c.setNumTarjeta(Integer.parseInt(textField8.getText()));
                    c.setFechaEntrada(Integer.parseInt(textField5.getText()));
                    c.setFechaSalida(Integer.parseInt(textField6.getText()));
                    if(estandarRadioButton.isSelected()) {
                        h.reservar(c, estandarRadioButton.getText(), Integer.parseInt(a0TextField.getText()));
                    }
                    if(balcónRadioButton.isSelected()) {
                        h.reservar(c, balcónRadioButton.getText(), Integer.parseInt(a0TextField1.getText()));
                    }
                    if(suiteRadioButton.isSelected()) {
                        h.reservar(c, suiteRadioButton.getText(), Integer.parseInt(a0TextField2.getText()));
                    }

                    textArea1.setText(h.toString());
                    precioFinal.setText("0€");
                    estandarRadioButton.setSelected(false);
                    balcónRadioButton.setSelected(false);
                    suiteRadioButton.setSelected(false);
                    a0TextField.setText("0");
                    a0TextField1.setText("0");
                    a0TextField2.setText("0");
                    JOptionPane.showMessageDialog(null,"¡Excelente, su reserva ha sido realizada con éxito!","Información",JOptionPane.INFORMATION_MESSAGE);

                    try {
                        salida.writeObject(h);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBox1.getSelectedItem()=="Sin desayuno") JOptionPane.showMessageDialog(null,"Has seleccionado sin desayuno","Aviso", JOptionPane.INFORMATION_MESSAGE);
                if(comboBox1.getSelectedItem()=="Con desayuno") JOptionPane.showMessageDialog(null,"Has seleccionado con desayuno","Aviso", JOptionPane.INFORMATION_MESSAGE);
                if(comboBox1.getSelectedItem()=="Media pensión") JOptionPane.showMessageDialog(null,"Has seleccionado media pensión","Aviso", JOptionPane.INFORMATION_MESSAGE);
                if(comboBox1.getSelectedItem()=="Pensión Completa") JOptionPane.showMessageDialog(null,"Has seleccionado pensión completa","Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        anularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileOutputStream fos = null;
                try{
                    fos = new FileOutputStream("HotelSerializar.dat");
                }catch (FileNotFoundException ex){
                    ex.printStackTrace();
                }
                ObjectOutputStream salida = null;
                try{
                    salida = new ObjectOutputStream(fos);
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                Cliente c = new Cliente();
                if(textField7.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"No se pudo efectuar su anulación.","ERROR",JOptionPane.ERROR_MESSAGE);
                }else{
                    c.setNombre(textField1.getText());
                    c.setApellido(textField2.getText());
                    c.setTelefono(Integer.parseInt(textField4.getText()));
                    c.setDNI(Integer.parseInt(textField7.getText()));
                    c.setNumTarjeta(Integer.parseInt(textField8.getText()));
                    c.setFechaEntrada(Integer.parseInt(textField5.getText()));
                    c.setFechaSalida(Integer.parseInt(textField6.getText()));
                    if(estandarRadioButton.isSelected()) {
                        h.anularReserva(c, estandarRadioButton.getText(), Integer.parseInt(a0TextField.getText()));
                    }
                    if(balcónRadioButton.isSelected()) {
                        h.anularReserva(c, balcónRadioButton.getText(), Integer.parseInt(a0TextField1.getText()));
                    }
                    if(suiteRadioButton.isSelected()) {
                        h.anularReserva(c, suiteRadioButton.getText(), Integer.parseInt(a0TextField2.getText()));
                    }
                    textArea1.setText(h.toString());
                    estandarRadioButton.setSelected(false);
                    balcónRadioButton.setSelected(false);
                    suiteRadioButton.setSelected(false);
                    a0TextField.setText("0");
                    a0TextField1.setText("0");
                    a0TextField2.setText("0");
                    try {
                        salida.writeObject(h);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    public void actionPerformed(ActionEvent e){
        if(dni.isSelected()) {
            Cliente c = new Cliente();
            if (h.estaHotel(c, Integer.parseInt(dni.getText()))) {
                c.setNombre(textField1.getText());
                c.setApellido(textField2.getText());
                c.setTelefono(Integer.parseInt(textField4.getText()));
                c.setNumTarjeta(Integer.parseInt(textField8.getText()));
                c.setFechaEntrada(Integer.parseInt(textField5.getText()));
                c.setFechaSalida(Integer.parseInt(textField6.getText()));
            }else{
                setContentPane(ventanaEmergente);
                setBounds(125,50,500,300);
                setVisible(false);
                setTitle("Error");
                noReservas.setText("No tiene reservas en el hotel");
            }
        }
        }


    }


