import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Navegacion {
    private JTextField textUrl;
    private JButton visitarButton;
    private JButton atrasButton;
    private JButton adelanteButton;
    private JTextArea textArea1;
    private JPanel panel1;
    private Stack<String> atras=new Stack<>();
    private Stack<String> adelante= new Stack<>();
    private String actual="";

    public Navegacion() {
        visitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
if (!actual.isEmpty()) atras.push(actual);
adelante.clear();
actual=textUrl.getText();
textUrl.setText("");
actualizar();
            }

        });
        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String anterior = atras.pop();
                    adelante.push(actual);
                    actual=anterior;
                    actualizar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "No hay paginas atras");
                }
            }
        });

        adelanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String siguiente= adelante.pop();
                    atras.push(actual);
                    actual=siguiente;
                    actualizar();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "No hay paginas adelante");
                }
            }
        });
        actualizar();
    }
    private void actualizar(){
        atrasButton.setEnabled(!atras.isEmpty());
        adelanteButton.setEnabled(!adelante.isEmpty());
        textArea1.setText("URL actual: "+actual + "\n\nAtras: "+atras + "\nAdelante:" +adelante);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Navegacion");
        frame.setContentPane(new Navegacion().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
