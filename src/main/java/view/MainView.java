package view;

import controller.ProfileController;
import model.Profile;
import session.Session;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainView extends JFrame {

    private JPanel MainViewPanel;
    private JButton signIn;
    private JButton registerButton;
    private JTextField searchTextField;
    private JTextPane leftPane;
    private JTextPane showSessionPane;

    public MainView(ProfileController profileController) {

        this.setTitle("Login");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setContentPane(MainViewPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (Session.getInstance().getLoggedInUser() != null) {
            showSessionPane.setText("Welcome " + Session.getInstance().getLoggedInUser().getUsername());
        }

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                showPanel("AuthView");
            }
        });

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Profile> profiles = profileController.listProfiles();
                StringBuilder allProfilesUsernames = new StringBuilder();
                for (int i = 0; i < profiles.size(); i++) {
                    allProfilesUsernames.append(profiles.get(i).getUsername() + " ");
                }
                leftPane.setText(String.valueOf(allProfilesUsernames));

            }
        });

    }

//    public void showPanel(String panelName) {
//        CardLayout cl = (CardLayout) (MainViewPanel.getLayout());
//        cl.show(MainViewPanel, panelName);
//    }
}
