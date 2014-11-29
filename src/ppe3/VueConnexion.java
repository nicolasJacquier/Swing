package ppe3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VueConnexion extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private AccesModele modele ;
	private JTextField tfUserId = new JTextField(10);
	private JPasswordField pfUserPass = new JPasswordField(10);
	private JLabel lbUserId = new JLabel("Identifiant : ");
	private JLabel lbUserPass = new JLabel("Mot de passe : ");
	private JLabel lbText = new JLabel("Bienvenue sur l'application GSB pour un délégué régional");
	private JLabel lbText1 = new JLabel("Veuillez entrez votre identifiant et mot de passe");
	private JButton bSeConnecter = new JButton("Se Connecter");
	private JPanel panel = new JPanel();
	private GridLayout layout = new GridLayout(3,1);
	/** Création de la vue
	 *
	 * @param modele Modèle de l'application
	 * @param controleur Controleur de l'application
	 */
	public VueConnexion(AccesModele modele, Controleur controleur){
		super();
		System.out.println("VueConnexion::VueConnexion()") ;
		this.modele = modele;
		this.controleur = controleur ;
		this.panel.setLayout(layout);
		this.lbUserId.setLabelFor(tfUserId);
		this.lbUserPass.setLabelFor(pfUserPass);
		this.bSeConnecter.addActionListener(this);
		panel.add(lbText);
		panel.add(lbText1);
		panel.add(bSeConnecter);
		this.add(this.panel);
	}
	
	/** Gérer les actions de l'utilisateur
	 *
	 * @param event L'action de l'utilisateur
	 */
	public void actionPerformed(ActionEvent e) {
		System.out.println("VueConnexion::actionPerformed()") ;
		Object sourceEvt = e.getSource() ;
		if(sourceEvt == this.bSeConnecter){
			Object[] content = {lbText1,lbUserId,tfUserId,lbUserPass,pfUserPass};
			int iResultJP = JOptionPane.showOptionDialog(this,content, "Fenêtre de connexion",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					null, null, null);
			if (iResultJP == JOptionPane.OK_OPTION){
				if( (tfUserId.getText().isEmpty() || pfUserPass.getText().isEmpty() ) == false ){
					boolean success = false;
					try {
						success = this.controleur.seConnecter(tfUserId.getText(),pfUserPass.getText());
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
					if(success == false) {
						JOptionPane.showMessageDialog(null,"Erreur : Veuillez vérifer l'identifiant ou le mot de passe saisi ");
						this.actualiser();
						this.tfUserId.setBackground(Color.PINK);
						this.pfUserPass.setBackground(Color.PINK);
						this.actionPerformed(e);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Erreur : Les champs sont vides ");
					this.actualiser();
					this.tfUserId.setBackground(Color.PINK);
					this.pfUserPass.setBackground(Color.PINK);
					this.actionPerformed(e);
				}
			}
			this.tfUserId.setBackground(Color.WHITE);;
			this.pfUserPass.setBackground(Color.WHITE);
			this.actualiser();
		}
	}
	/** Actualiser les champs
	 */
	public void actualiser(){
		System.out.println("VueConnexion::actualiser()") ;
		this.tfUserId.setText("");
		this.pfUserPass.setText("");
	}

}
