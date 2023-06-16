import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorGUI extends JFrame {
    private JLabel currentFloorLabel;
    private JButton[] floorButtons;
    private JButton stopButton;
    private JTextArea logTextArea;
    private JPanel liftPanel;

    private int currentFloor = 1;
    private int totalFloors;
    private boolean isMoving;
    private int liftPosition;

    public ElevatorGUI(int totalFloors) {
        this.totalFloors = totalFloors;
        isMoving = false;
        liftPosition = 0;

        setTitle("Elevator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create GUI components
        currentFloorLabel = new JLabel("Current Floor: " + currentFloor);
        currentFloorLabel.setFont(new Font("Arial", Font.BOLD, 16));

        floorButtons = new JButton[totalFloors];
        for (int i = 0; i < totalFloors; i++) {
            int floor = i + 1;
            floorButtons[i] = new JButton("Floor " + floor);
            floorButtons[i].setFont(new Font("Arial", Font.PLAIN, 12));
            floorButtons[i].setBackground(new Color(51, 153, 255));
            floorButtons[i].setForeground(Color.WHITE);
            floorButtons[i].addActionListener(new FloorButtonListener(floor));
        }

        stopButton = new JButton("Stop");
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setBackground(new Color(255, 102, 102));
        stopButton.setForeground(Color.WHITE);
        stopButton.addActionListener(new StopButtonListener());

        logTextArea = new JTextArea(10, 20);
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        logTextArea.setBackground(new Color(240, 240, 240));

        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        liftPanel = new JPanel();
        liftPanel.setPreferredSize(new Dimension(100, 100));
        liftPanel.setBackground(new Color(200, 200, 200));
        liftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Set layout and add components to the frame
        JPanel floorButtonPanel = new JPanel(new GridLayout(totalFloors, 1, 0, 5));
        floorButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < totalFloors; i++) {
            floorButtonPanel.add(floorButtons[i]);
        }

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBackground(new Color(240, 240, 240));
        controlPanel.add(currentFloorLabel, BorderLayout.NORTH);
        controlPanel.add(floorButtonPanel, BorderLayout.CENTER);
        controlPanel.add(stopButton, BorderLayout.SOUTH);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(new Color(240, 240, 240));
        contentPane.add(controlPanel, BorderLayout.WEST);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(liftPanel, BorderLayout.EAST);

        setContentPane(contentPane);

        // Start the timer
        Timer timer = new Timer(1000, new TimerListener());
        timer.start();
    }

    private class FloorButtonListener implements ActionListener {
        private int floor;

        public FloorButtonListener(int floor) {
            this.floor = floor;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isMoving) {
                logTextArea.append("Moving to Floor: " + floor + "\n");
                isMoving = true;

                if (currentFloor == floor) {
                    logTextArea.append("You are already on Floor " + floor + "\n");
                    isMoving = false;
                } else {
                    int direction = (currentFloor < floor) ? 1 : -1;
                    Thread elevatorThread = new Thread(new ElevatorRunnable(direction, floor));
                    elevatorThread.start();
                }
            }
        }
    }

    private class StopButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isMoving) {
                logTextArea.append("Elevator stopped at Floor " + currentFloor + "\n");
                isMoving = false;
            }
        }
    }

    private class ElevatorRunnable implements Runnable {
        private int direction;
        private int targetFloor;

        public ElevatorRunnable(int direction, int targetFloor) {
            this.direction = direction;
            this.targetFloor = targetFloor;
        }

        @Override
        public void run() {
            Timer timer = new Timer(20, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    liftPosition += direction;
                    liftPanel.setLocation(liftPanel.getX(), liftPosition);

                    if (liftPosition % 100 == 0) {
                        currentFloor += direction;
                        currentFloorLabel.setText("Current Floor: " + currentFloor);

                        if (currentFloor == targetFloor) {
                            logTextArea.append("Arrived at Floor: " + currentFloor + "\n");
                            isMoving = false;
                            ((Timer) e.getSource()).stop();
                        } else {
                            logTextArea.append("Passing Floor: " + currentFloor + "\n");
                        }
                    }
                }
            });

            timer.start();
        }
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isMoving) {
                liftPanel.setBackground(new Color(200, 200, 200));
            } else {
                Color currentColor = liftPanel.getBackground();
                Color newColor = (currentColor == Color.RED) ? Color.GREEN : Color.RED;
                liftPanel.setBackground(newColor);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int totalFloors = 10; // Set the total number of floors for the elevator
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ElevatorGUI gui = new ElevatorGUI(totalFloors);
            gui.pack();
            gui.setVisible(true);
        });
    }
}
