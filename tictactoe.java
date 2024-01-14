import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TTTGame extends JFrame implements ItemListener, ActionListener {
    int i;
    int j;
    int ii;
    int jj;
    int x;
    int y;
    int yesnull;
    int[][] a = new int[][]{{10, 1, 2, 3, 11}, {10, 1, 4, 7, 11}, {10, 1, 5, 9, 11}, {10, 2, 5, 8, 11}, {10, 3, 5, 7, 11}, {10, 3, 6, 9, 11}, {10, 4, 5, 6, 11}, {10, 7, 8, 9, 11}};
    int[][] a1 = new int[][]{{10, 1, 2, 3, 11}, {10, 1, 4, 7, 11}, {10, 1, 5, 9, 11}, {10, 2, 5, 8, 11}, {10, 3, 5, 7, 11}, {10, 3, 6, 9, 11}, {10, 4, 5, 6, 11}, {10, 7, 8, 9, 11}};
    boolean state;
    boolean type;
    boolean set;
    Icon ic1;
    Icon ic2;
    Icon icon;
    Icon ic11;
    Icon ic22;
    Checkbox c1;
    Checkbox c2;
    JLabel l1;
    JLabel l2;
    JButton[] b = new JButton[9];
    JButton reset;
    JPanel backgroundPanel;
    double titleLabelAngle = 0.0;
    double titleLabelFrequency = 0.1;
    double titleLabelAmplitude = 10.0;
    int titleLabelX = 100;
    int titleLabelY = 20;
    Timer titleLabelTimer;
    JLabel titleLabel;
    JLabel labelVsComputer;
    JLabel labelVsFriend;

    private void animateTitleLabel() {
        titleLabelAngle += titleLabelFrequency;
    
        int newX = (int) (titleLabelX + titleLabelAmplitude * Math.sin(titleLabelAngle));
        int newY = titleLabelY;
    
        titleLabel.setLocation(newX, newY);
    }

    private void startAnimation() {
        titleLabelTimer.start();
    }
    public void showButton() {
        this.x = 10;
        this.y = 10;
        this.j = 0;
    
        for (this.i = 0; this.i <= 8; ++this.j) {
            this.b[this.i] = new JButton();
            if (this.j == 3) {
                this.j = 0;
                this.y += 100;
                this.x = 10;
            }
    
            this.b[this.i].setBounds(this.x, this.y, 100, 100);
            this.add(this.b[this.i]);
            this.b[this.i].addActionListener(this);
            ++this.i;
            this.x += 100;
        }
    
        if (this.type) {
          // Tambahkan JLabel untuk judul "TIC TAC TOE"
        titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setForeground(Color.BLUE); 
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(100, 20, 150, 40);
        add(titleLabel);

        this.labelVsComputer = new JLabel(new ImageIcon("vs_computer_image.jpg"));
        this.labelVsComputer.setBounds(230, 80, 50, 40);
        add(this.labelVsComputer);

        this.labelVsFriend = new JLabel(new ImageIcon("vs_friend_image.jpg"));
        this.labelVsFriend.setBounds(230, 150, 50, 40);
        add(this.labelVsFriend);
        }
        if (this.type) {
            titleLabel = new JLabel("TIC TAC TOE");
            titleLabel.setForeground(Color.BLUE);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setBounds(titleLabelX, titleLabelY, 150, 40);
            add(titleLabel);
            startAnimation(); // Memulai animasi setelah menampilkan frame
        }
        reset = new JButton("Reset");
        reset.setBounds(120, 350, 80, 40);
        add(reset);
        reset.addActionListener(this);

        add(backgroundPanel);
    backgroundPanel.setLayout(null);
    this.repaint();
    }
    
    public void check(int var1) {
        for (this.ii = 0; this.ii <= 7; ++this.ii) {
            for (this.jj = 1; this.jj <= 3; ++this.jj) {
                if (this.a[this.ii][this.jj] == var1) {
                    this.a[this.ii][4] = 11;
                }
            }
        }
    }

    public void compLogic(int var1) {
        for (this.i = 0; this.i <= 7; ++this.i) {
            for (this.j = 1; this.j <= 3; ++this.j) {
                if (this.a[this.i][this.j] == var1) {
                    this.a[this.i][0] = 11;
                    this.a[this.i][4] = 10;
                }
            }
        }

        for (this.i = 0; this.i <= 7; ++this.i) {
            this.set = true;
            if (this.a[this.i][4] == 10) {
                int var2 = 0;

                for (this.j = 1; this.j <= 3; ++this.j) {
                    if (this.b[this.a[this.i][this.j] - 1].getIcon() != null) {
                        ++var2;
                    } else {
                        this.yesnull = this.a[this.i][this.j];
                    }
                }

                if (var2 == 2) {
                    this.b[this.yesnull - 1].setIcon(this.ic2);
                    this.check(this.yesnull);
                    this.set = false;
                    break;
                }
            } else if (this.a[this.i][0] == 10) {
                for (this.j = 1; this.j <= 3; ++this.j) {
                    if (this.b[this.a[this.i][this.j] - 1].getIcon() == null) {
                        this.b[this.a[this.i][this.j] - 1].setIcon(this.ic2);
                        this.check(this.a[this.i][this.j]);
                        this.set = false;
                        break;
                    }
                }

                if (!this.set) {
                    break;
                }
            }

            if (!this.set) {
                break;
            }
        }
    }

    private void centerFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
    
        int frameWidth = getWidth();
        int frameHeight = getHeight();
    
        int x = (screenWidth - frameWidth) / 2;
        int y = (screenHeight - frameHeight) / 2;
    
        setLocation(x, y);
    }
    
    private Color titleLabelColor;
    private Timer colorChangeTimer;
    TTTGame() {
        super("Tic Tac Toe by MUTIARA");
        CheckboxGroup var1 = new CheckboxGroup();
        // Tambahkan JLabel untuk judul "TIC TAC TOE"
        titleLabel = new JLabel("TIC TAC TOE");
        titleLabel.setForeground(Color.BLUE); // Set warna teks menjadi biru
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font dan ukuran teks
        titleLabel.setBounds(100, 20, 150, 40);
        add(titleLabel);
        this.c1 = new Checkbox("vs computer", var1, false);
        this.c2 = new Checkbox("vs friend", var1, false);
        this.c1.setBounds(120, 80, 100, 40);
        this.c2.setBounds(120, 150, 100, 40);
        
        // Tambahkan JLabel untuk menampilkan gambar di sebelah checkbox
        this.labelVsComputer = new JLabel(new ImageIcon("vs_computer_image.jpg"));
        this.labelVsComputer.setBounds(230, 80, 50, 40);
        add(this.labelVsComputer);

        this.labelVsFriend = new JLabel(new ImageIcon("vs_friend_image.jpg"));
        this.labelVsFriend.setBounds(230, 150, 50, 40);
        add(this.labelVsFriend);
            
        
        this.add(this.c1);
        this.add(this.c2);
        this.add(labelVsComputer);
        this.add(labelVsFriend);

        this.c1.addItemListener(this);
        this.c2.addItemListener(this);
        this.state = true;
        this.type = true;
        this.set = true;
        this.ic1 = new ImageIcon("ic1.jpg");
        this.ic2 = new ImageIcon("ic2.jpg");
        this.ic11 = new ImageIcon("ic11.jpg");
        this.ic22 = new ImageIcon("ic22.jpg");
        this.setLayout(null);
        this.setSize(330, 450);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, 330, 450);
        backgroundPanel.setBackground(Color.LIGHT_GRAY);
        
        titleLabelTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            animateTitleLabel();
        }
    });

    titleLabelColor = Color.BLUE;
        colorChangeTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTitleLabelColor();
            }
        });
        centerFrame();
        startColorChange();
    startAnimation();
}

private void changeTitleLabelColor() {
    // Ganti warna secara acak
    int red = (int) (Math.random() * 256);
    int green = (int) (Math.random() * 256);
    int blue = (int) (Math.random() * 256);
    titleLabelColor = new Color(red, green, blue);

    // Setel warna tulisan
    titleLabel.setForeground(titleLabelColor);
}

private void startColorChange() {
    colorChangeTimer.start();
}


    public void itemStateChanged(ItemEvent var1) {
        if (this.c1.getState()) {
            this.type = false;
        } else if (this.c2.getState()) {
            this.type = true;
        }
    
        this.remove(this.c1);
        this.remove(this.c2);
        this.repaint(0, 0, 330, 450);
    
        // Hapus judul dan gambar setelah memilih mode
        if (titleLabel != null) {
            this.remove(this.titleLabel);
        }
        
        // Hapus labelVsComputer dan labelVsFriend
        
        if (this.labelVsComputer != null) {
            this.remove(this.labelVsComputer);
        }
        if (this.labelVsFriend != null) {
            this.remove(this.labelVsFriend);
        }

    
        this.repaint(0, 0, 330, 450);
        this.showButton();
    }
    

    public void actionPerformed(ActionEvent var1) {
        if (this.type) {
            if (var1.getSource() == this.reset) {
                resetGame();
            } else {
                for (this.i = 0; this.i <= 8; ++this.i) {
                    if (var1.getSource() == this.b[this.i] && this.b[this.i].getIcon() == null) {
                        if (this.state) {
                            this.icon = this.ic2;
                            this.state = false;
                        } else {
                            this.icon = this.ic1;
                            this.state = true;
                        }

                        this.b[this.i].setIcon(this.icon);
                    }
                }
            }
        } else if (!this.type) {
            if (var1.getSource() == this.reset) {
                resetGame();
            } else {
                for (this.i = 0; this.i <= 8; ++this.i) {
                    if (var1.getSource() == this.b[this.i] && this.b[this.i].getIcon() == null) {
                        this.b[this.i].setIcon(this.ic1);
                        if (this.b[4].getIcon() == null) {
                            this.b[4].setIcon(this.ic2);
                            this.check(5);
                        } else {
                            this.compLogic(this.i);
                        }
                    }
                }
            }
        }

        for (this.i = 0; this.i <= 7; ++this.i) {
            Icon var2 = this.b[this.a[this.i][1] - 1].getIcon();
            Icon var3 = this.b[this.a[this.i][2] - 1].getIcon();
            Icon var4 = this.b[this.a[this.i][3] - 1].getIcon();
            if (var2 == var3 && var3 == var4 && var2 != null) {
                if (var2 == this.ic1) {
                    this.b[this.a[this.i][1] - 1].setIcon(this.ic11);
                    this.b[this.a[this.i][2] - 1].setIcon(this.ic11);
                    this.b[this.a[this.i][3] - 1].setIcon(this.ic11);
                    JOptionPane.showMessageDialog(this, "!!!KAMU MENANG!!! KLIK reset.");
                    break;
                }

                if (var2 == this.ic2) {
                    this.b[this.a[this.i][1] - 1].setIcon(this.ic22);
                    this.b[this.a[this.i][2] - 1].setIcon(this.ic22);
                    this.b[this.a[this.i][3] - 1].setIcon(this.ic22);
                    JOptionPane.showMessageDialog(this, "!!!HAHAHA (COMPUTER) MENANG!!! KLIK reset.");
                    break;
                }
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i <= 8; ++i) {
            b[i].setIcon(null);
        }

        for (int i = 0; i <= 7; ++i) {
            System.arraycopy(a1[i], 0, a[i], 0, 5);
        }
    }
    
    public static void main(String[] var0) {
        new TTTGame();
    }
}
