package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charva.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import charva.awt.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This class demonstrates how to run a long-duration task in a separate
 * thread and display the task's progress in a JProgressBar component.
 */
class ProgressBarTest
        extends JDialog
        implements ActionListener {

    private JProgressBar _progressBar = new JProgressBar();
    private Thread _taskThread;

    ProgressBarTest(Frame owner_) {
        super(owner_, "JProgressBar Test");
        Container contentPane = getContentPane();

        contentPane.add(makeNorthPanel(), BorderLayout.NORTH);

        contentPane.add(makeCenterPanel(), BorderLayout.CENTER);

        JPanel southpan = new JPanel();
        JButton startButton = new JButton("Start Task");
        startButton.addActionListener(this);
        southpan.add(startButton);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        southpan.add(cancelButton);

        contentPane.add(southpan, BorderLayout.SOUTH);

        pack();
    }

    /**
     * Implements the ActionListener interface
     */
    public void actionPerformed(ActionEvent e_) {
        String cmd = e_.getActionCommand();
        if (cmd.equals("Start Task")) {
            if (_taskThread != null && _taskThread.isAlive()) {
                JOptionPane.showMessageDialog(this, "The task is already running",
                        "Error", JOptionPane.PLAIN_MESSAGE);
            } else {
                _taskThread = new TaskThread();
                _taskThread.start();
            }
        } else if (cmd.equals("OK")) {
            if (_taskThread != null && _taskThread.isAlive()) {
                JOptionPane.showMessageDialog(this, "The task is still running",
                        "Error", JOptionPane.PLAIN_MESSAGE);
            } else
                hide();
        } else if (cmd.equals("Cancel")) {
            if (_taskThread != null && _taskThread.isAlive()) {
                _taskThread.interrupt();
            }
            hide();
        }
    }

    private JPanel makeNorthPanel() {
        JPanel northpan = new JPanel();
        northpan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(1, 1, 1, 1);
        JLabel label = new JLabel("Press START TASK to run a long task in a separate thread.");
        northpan.add(label, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 1, 0, 1);
        label = new JLabel("While the task is running, press TAB and then enter some");
        northpan.add(label, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 1, 0, 1);
        label = new JLabel("text in the TextField to verify that the user interface");
        northpan.add(label, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 1, 1, 1);
        label = new JLabel("is still responsive.");
        northpan.add(label, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 1, 0, 1);
        label = new JLabel("The progress bar will start in indeterminate mode, indicating");
        northpan.add(label, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 1, 0, 1);
        label = new JLabel("that the task duration is initially unknown; then it will");
        northpan.add(label, gbc);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 1, 1, 1);
        label = new JLabel("change to determinate mode.");
        northpan.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        northpan.add(new JLabel("Enter some text here:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        northpan.add(new JTextField(20), gbc);
        gbc.insets = new Insets(0, 1, 1, 1);

        return northpan;
    }

    private JPanel makeCenterPanel() {
        JPanel centerpan = new JPanel();
        centerpan.setBorder(new TitledBorder("Task Progress"));
        centerpan.setLayout(new BorderLayout());

        _progressBar.setStringPainted(true);
        centerpan.add(_progressBar, BorderLayout.CENTER);

        return centerpan;
    }

    /**
     * A nonstatic inner class that pretends to perform a time-consuming task.
     */
    private class TaskThread extends Thread {

        private final Log logger = LogFactory.getLog(TaskThread.class);

        /**
         * Constructor
         */
        private TaskThread() {
        }

        /**
         * Pretend to do a task that takes a long time.
         * Twice per second, wake up and update the progress bar. Note that
         * since this thread is not the event-dispatching thread, we cannot
         * manipulate the screen components directly; instead, we must
         * call the static method "invokeLater()" of the SwingUtilities class,
         * which will cause the event-dispatching thread to update the progress
         * bar.
         * See "Core Java, Volume II" by Horstmann and Cornell, chapter 1;
         * Also see
         * http://java.sun.com/docs/books/tutorial/uiswing/overview/threads.html
         */
        public void run() {

            try {
                // Initially, set the progressbar to indeterminate mode
                // for 5 seconds (i.e. pretend we don't initially know
                // the duration of the task).
                _progressBar.setIndeterminate(true);
                Thread.sleep(5000L);
                _progressBar.setIndeterminate(false);

                for (int percent = 0; percent <= 100; percent += 2) {

                    Thread.sleep(500L);
                    Runnable updater = new ProgressBarUpdater(percent);
                    SwingUtilities.invokeLater(updater);
                }
            } catch (InterruptedException e) {
                logger.warn("TaskThread was interrupted");
                return;
            }
        }
    }

    /**
     * This is a nonstatic inner class that implements the Runnable interface;
     * instances of this can be passed to the SwingUtilities.invokeLater() method.
     * A shortcut method of invoking code in the event-dispatch thread,
     * involving the use of anonymous inner classes, is shown in "Core Java
     * Volume II" by Horstmann and Cornell, chapter 1, in the "Threads and
     * Swing" subsection.
     */
    private class ProgressBarUpdater implements Runnable {
        private int _percent;

        private ProgressBarUpdater(int percent_) {
            _percent = percent_;
        }

        public void run() {
            String str = Integer.toString(_percent) + "%";
            _progressBar.setString(str);
            _progressBar.setValue(_percent);
        }
    }
}

