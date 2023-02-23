import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class HelloSwing extends JFrame{
    public static void main(String[] agrs){
       new HelloSwing();
    }
    
    Random random = new Random();
    char[][] table = new char[3][3];
        
    
    HelloSwing(){
        initTable();
        setTitle("Hello");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,450);
        setLocationRelativeTo(null);
                
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.white);
        canvas.setPreferredSize(new Dimension(300,300));
        canvas.addMouseListener(new MouseAdapter () {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX() / 100;
                int y = e.getY() / 100;
                
                System.out.println(x + " " + y);
                
                if (table[y][x] == '.') {
                    table[y][x] = 'x';
                    
                    do {
                        x = random.nextInt(3);
                        y = random.nextInt(3);
                    } while (table[y][x] != '.');
                    table[y][x] = 'o';
                    
                    canvas.repaint();
                }
                
            }
        });
        
        Button reload = new Button("Reload");
        Button exit = new Button("Exit");
        
        reload.addActionListener(e -> {
            initTable();
            System.out.println("reload1");
            canvas.repaint();
            
        });
        exit.addActionListener(e -> System.exit(0));
        
        JPanel panel = new JPanel();
        panel.add(reload);
        panel.add(exit);
        
        super.add(canvas, BorderLayout.CENTER);
        super.add(panel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    void initTable(){
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                table[y][x] = '.'; 
            }
        }    
    }
    
    class Canvas extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawLine(0,100,300,100);
            g.drawLine(0,200,300,200);
            g.drawLine(100,0,100,300);
            g.drawLine(200,0,200,300);
            
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    
                    if (table[y][x] == 'x') {
                        g.setColor(Color.blue);
                        g.drawLine(x * 100, y * 100, (x + 1) * 100, (y + 1) * 100);
                        g.drawLine((x + 1) * 100, y * 100, x * 100, (y + 1) * 100);
                    }
                    
                    if (table[y][x] == 'o') {
                        g.setColor(Color.red);
                        g.drawOval(x * 100, y * 100, 100, 100);
                    }
                }
            }
                
        }
    }
}