import java.awt.Desktop;
import org.jdom.filter.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.itextpdf.text.DocumentException;

import javax.swing.JDialog;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author XML_V2.0
 */
public class mode extends javax.swing.JFrame {
	Ajoute_Objet add= new Ajoute_Objet();
	final DefaultListModel<String> modell = new DefaultListModel();
	ArrayList<String> liste_objett= new ArrayList<>();
	ArrayList<String> liste_objet= new ArrayList<>();
	String url="";
	 int cmpt=0;


	static int numero =0;
	
	
	static Connexion c = new Connexion();
	static mode menu1 = new mode();
	ArrayList<String>liste_data = new ArrayList<>();
	DefaultListModel<String> model = new DefaultListModel<String>();
	ArrayList<String>Liste_client = new ArrayList<>();
	ArrayList<String>Liste_objet = new ArrayList<>();
	public static final String RESULT = "../XML_engine/src/PDF/Contrat.pdf";
	
	

    /**
     * Creates new form menu
     */
    public mode() {
        initComponents();
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(jRadioButton_categorie_Recherche1);
        buttons.add(jRadioButton_Zone_Recherche1);
        buttons.add(jRadioButton_Autre_Recherche1);
        jComboBox_Categorie_Recherche1.setEnabled(false);
        jComboBox_Zone_Recherche1.setEnabled(false);
        jTextField_Autre_Recherche1.setEnabled(false);
        jButton_recherche_simple1.setEnabled(false);
        Chrgement_client();
        Chrgement_objet();
        
        jTextField_titre_Supprimer.setEnabled(true);
        DefaultListModel modell = new DefaultListModel();
        
        for (int i = 0; i < liste_objett.size(); i++) {
        	model.addElement(liste_objett.get(i));
        	}
        jList_Supprimer.setModel(modell);
        
        
        
        for (int i = 0; i < Liste_client.size(); i++) {
        	jComboBox_Personnes_concernees4.addItem(Liste_client.get(i));
        	jComboBox_Personnes_concernees6.addItem(Liste_client.get(i));

		}
        for (int i = 0; i < Liste_objet.size(); i++) {
        	jComboBox_Objetes_concernees4.addItem(Liste_objet.get(i));
        	jComboBox_Objetes_concernees6.addItem(Liste_objet.get(i));

		}
    }

   
    /**
       * --------------------------DEBUT_D_AJOUTER_UN_OBJET________________________
        */
       //supression de la derrn ligne valise personne
       public  void remove_ligne() throws IOException {
       	int cpt=0;
           Vector monVector = new Vector(); 
           File f = new File("../XML_engine/src/Fiche_objet.xml"); 
           BufferedReader B = new BufferedReader(new FileReader(f)); 
           String ligne = B.readLine(); 
           while (ligne != null){ 
               monVector.addElement(ligne); 
               ligne = B.readLine(); 
               cpt++;
           } 
           monVector.removeElementAt(cpt-1); 
           PrintWriter P = new PrintWriter (new FileWriter(f)); 
           for (int i = 0; i < monVector.size(); i++){ 
               P.println(monVector.get(i)); 
           } 
           P.close(); 
   	}
       //function d'ajoute sur le fichier xml
       public  void append(String prop,String titre, String categorie,
       		String couleur,String dimension,String echange,String contre,String zone,
       			String numero,String url) {
       	
       	try {
   			remove_ligne();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
           BufferedWriter bufWriter = null;
           FileWriter fileWriter = null;
           try {
               fileWriter = new FileWriter("../XML_engine/src/Fiche_objet.xml", true);
               bufWriter = new BufferedWriter(fileWriter);
               //Inserer un saut de ligne
               bufWriter.newLine();
               bufWriter.write("<objet>");
               bufWriter.newLine();
               bufWriter.write("<propritaire>"+prop+"</propritaire>");
               bufWriter.newLine();
               bufWriter.write("<titre>"+titre+"</titre>");
               bufWriter.newLine();
               bufWriter.write("<categorie>"+categorie+"</categorie>");
               bufWriter.newLine();
               bufWriter.write("<couleur>"+couleur+"</couleur>");
               bufWriter.newLine();
               bufWriter.write("<dimension>"+dimension+"</dimension>");
               bufWriter.newLine();
               bufWriter.write("<echange>"+echange+"</echange>");
               bufWriter.newLine();
               bufWriter.write("<contre>"+contre+"</contre>");
               bufWriter.newLine();
               bufWriter.write("<zone>"+zone+"</zone>");
               bufWriter.newLine();
               bufWriter.write("<numero>"+numero+"</numero>");
               bufWriter.newLine();
               bufWriter.write("<url>"+url+"</url>");
               bufWriter.newLine();
               bufWriter.write("</objet>");
               bufWriter.newLine();
               bufWriter.write("</items>");
               bufWriter.newLine();
           } catch (IOException ex) {
               System.out.println("Erreur");
           } finally {
               try {
                   bufWriter.close();
                   fileWriter.close();
               } catch (IOException ex) {
                   System.out.println("Erreur");
               }
           }
       }
       private void jButton_valider_AjouterActionPerformed(java.awt.event.ActionEvent evt) { 
                                                       
           // TODO add your handling code here:
       	if(jTextField_dimension_Ajouter.getText().equals("")
       			||jTextField_titre_Ajouter.getText().equals("")||
       			jTextField_couleur_Ajouter.getText().equals("")||
       			jTextArea_echange_Ajouter.getText().equals("")||
       			jTextArea_contre_Ajouter.getText().equals("")||
       			jTextField_telephone_Ajouter.getText().equals("")){
       		JOptionPane d = new JOptionPane();
       		d.showMessageDialog( this, "Vous devez remplire tout les champs", "le titre", JOptionPane.ERROR_MESSAGE);
       		}else{
       			append(c.client.getAdresse_mel(),
       					jTextField_titre_Ajouter.getText(),
       					jComboBox_Categorie_Ajouter.getSelectedItem().toString(),	
       					jTextField_couleur_Ajouter.getText(),
       					jTextField_dimension_Ajouter.getText(),
       					jTextArea_echange_Ajouter.getText(),
       					jTextArea_contre_Ajouter.getText(),
       					jComboBox_zone_Ajouter.getSelectedItem().toString(),
       					jTextField_telephone_Ajouter.getText(),
       					Ajouter_image_Ajouter.getText());
       			this.setVisible(true);
    	       	JOptionPane.showMessageDialog(this, "votre objet a  été bien enregistrée .");

       			}

		
		 if(evt.getSource()==jButton_valider_Ajouter)  
		 {    
			    jTextArea_contre_Ajouter.setText("");
		       	jTextField_dimension_Ajouter.setText("");
		       	jTextField_titre_Ajouter.setText("");
				jTextField_couleur_Ajouter.setText("");
				jTextArea_echange_Ajouter.setText("");
				jTextField_telephone_Ajouter.setText("");
				Ajouter_image_Ajouter.setText("");


		 }

			

       
       	}                                                       
       private void Ajouter_image_AjouterActionPerformed(java.awt.event.ActionEvent evt) {
       	JFileChooser chooser = new JFileChooser();
       	FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
       	chooser.setFileFilter(filter);
       	int returnVal = chooser.showOpenDialog(null);
       	if(returnVal == JFileChooser.APPROVE_OPTION) {
       		Ajouter_image_Ajouter.setText(chooser.getSelectedFile().getName());
       		}

           // TODO add your handling code here:
       	}
       /**
        * --------------------------FIN_D_AJOUTER_UN_OBJET________________________
        */

         
       /**
        * --------------------------DEBUT_RECHERCHE________________________________
        */
       
       //cette fonction premet de charger la liste
       public void charge_liste(String type){
       	System.out.println("aaaaaaa "+type);
     	  org.jdom.Document doc = null;
   	    Element rac;
     	   SAXBuilder sxb = new SAXBuilder();
     	      try
     	      {
     	         //On cree un nouveau document JDOM avec en argument le fichier XML
     	         //Le parsing est termine ;)
     	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
     	      }
     	      catch(Exception e){}
     	      //On initialise un nouvel element racine avec l'element racine du document.
     	      rac = doc.getRootElement();
     	      List listEtudiants = rac.getChildren("objet");
     	      Iterator i = listEtudiants.iterator();
    	      while(i.hasNext())
    	      {
    	   	   Element courant = (Element)i.next();
    	   	     
    	         if(type.equals(courant.getChild("categorie").getText())||
    	        		type.equals(courant.getChild("zone").getText()) ||
    	        		type.equals(courant.getChild("titre").getText())){
    	        	liste_data.add(courant.getChild("titre").getText());
    	         } 
    	         
    	      }
    	      
    	      
    	      
       }
       private void jRadioButton_Zone_Recherche1ActionPerformed(java.awt.event.ActionEvent evt) {                                                             
           // TODO add your handling code here:
       	if(jRadioButton_Zone_Recherche1.isSelected())
       		jComboBox_Zone_Recherche1.setEnabled(true);
       	jComboBox_Categorie_Recherche1.setEnabled(false);
       	jTextField_Autre_Recherche1.setEnabled(false);
       	jButton_recherche_simple1.setEnabled(false);
       }                                                             
       private void jRadioButton_categorie_Recherche1ActionPerformed(java.awt.event.ActionEvent evt) {                                                                  
           // TODO add your handling code here:
       	if(jRadioButton_categorie_Recherche1.isSelected())
       		jComboBox_Zone_Recherche1.setEnabled(false);
       	jComboBox_Categorie_Recherche1.setEnabled(true);
       	jTextField_Autre_Recherche1.setEnabled(false);
       	jButton_recherche_simple1.setEnabled(false);
       } 
       private void jRadioButton_Autre_Recherche1ActionPerformed(java.awt.event.ActionEvent evt) {                                                              
           // TODO add your handling code here:
       	if(jRadioButton_Autre_Recherche1.isSelected())
       		jComboBox_Zone_Recherche1.setEnabled(false);
       	jComboBox_Categorie_Recherche1.setEnabled(false);
       	jTextField_Autre_Recherche1.setEnabled(true);
       	jButton_recherche_simple1.setEnabled(true);
       	
       } 
       private void jComboBox_Zone_Recherche1ActionPerformed(java.awt.event.ActionEvent evt) {                                                          
           // TODO add your handling code here:
       	traitement((String)jComboBox_Zone_Recherche1.getSelectedItem());

       }                                                         
       private void jComboBox_Categorie_Recherche1ActionPerformed(java.awt.event.ActionEvent evt) {                                                               
           // TODO add your handling code here:
       	traitement((String)jComboBox_Categorie_Recherche1.getSelectedItem());

       }                                                              
       private void jTextField_Autre_Recherche1ActionPerformed(java.awt.event.ActionEvent evt) {                                                            
           // TODO add your handling code here:
       } 
       private void jButton_recherche_simple1ActionPerformed(java.awt.event.ActionEvent evt) {                                                          
           // TODO add your handling code here:
       	traitement(jTextField_Autre_Recherche1.getText());

       }
       private void jList_cherche1MouseClicked(java.awt.event.MouseEvent evt) {
       	jTextArea_Contre_Recherche1.setText("");
       	jTextArea_Echange_Recherche1.setText("");
       	load_information();
       }
       public void load_information(){
        	  org.jdom.Document doc = null;
      	    Element rac;
        	   SAXBuilder sxb = new SAXBuilder();
        	      try
        	      {
        	         //On cree un nouveau document JDOM avec en argument le fichier XML
        	         //Le parsing est termine ;)
        	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
        	      }
        	      catch(Exception e){}

        	      //On initialise un nouvel element racine avec l'element racine du document.
        	      rac = doc.getRootElement();
       	   List listEtudiants = rac.getChildren("objet");

       	   Iterator i = listEtudiants.iterator();

       	      while(i.hasNext())
       	      {
       	     
       	    	   Element courant = (Element)i.next();
       	    
       	      
       	         if(jList_cherche1.getSelectedValue().equals(courant.getChild("titre").getText())){
       	       
       	        	 jLabel_couleur_Recherche1.setText(courant.getChild("couleur").getText());
       	        	 jLabel_dimension_Recherche1.setText(courant.getChild("dimension").getText());
       	        	 jTextArea_Echange_Recherche1.append(courant.getChild("echange").getText());
       	        	 jTextArea_Contre_Recherche1.append(courant.getChild("contre").getText());
       	        	 jLabel_Telephone_Recherche1.setText(courant.getChild("numero").getText());
       	        	 jLabel_image_rechreche1.setIcon(new javax.swing.ImageIcon("../XML_engine/src/Image/"+courant.getChild("url").getText())); // NOI18N

       	         }
       	         
       	      }
         }
       public  void traitement(String selection){
       	charge_liste(selection);
    		try {
   			 ((DefaultListModel<String>) jList_cherche1.getModel()).removeAllElements();
   		} catch (Exception e) {
   			// TODO: handle exception
   		}
    		
   		for (int i = 0; i < liste_data.size(); i++) {
   	    	model.addElement(liste_data.get(i));
   		}

   		jList_cherche1.setModel(model);

   		   liste_data.clear();
       }
       
       /**
        *  --------------------------Debut_Recherche_par_filtre

        * 
        */
       public void charge_liste10(String type,String Type1,String type2){
          	System.out.println("aaaaaaa "+type);
        	  org.jdom.Document doc = null;
      	    Element rac;
        	   SAXBuilder sxb = new SAXBuilder();
        	      try
        	      {
        	         //On cree un nouveau document JDOM avec en argument le fichier XML
        	         //Le parsing est termine ;)
        	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
        	      }
        	      catch(Exception e){}
        	      //On initialise un nouvel element racine avec l'element racine du document.
        	      rac = doc.getRootElement();
        	      List listEtudiants = rac.getChildren("objet");
        	      Iterator i = listEtudiants.iterator();
       	      while(i.hasNext())
       	      {
       	   	   Element courant = (Element)i.next();
       	         if((type.equals(courant.getChild("categorie").getText()) ||
       	        		type.equals(courant.getChild("zone").getText()) ||
       	        		type.equals(courant.getChild("titre").getText()))
       	        		&& (Type1.equals(courant.getChild("categorie").getText()) ||
       	        				Type1.equals(courant.getChild("zone").getText()) ||
       	        				Type1.equals(courant.getChild("titre").getText()))&&
       	       	        		
       	       	        	(type2.equals(courant.getChild("categorie").getText()) ||
       	       	        		type2.equals(courant.getChild("zone").getText()) ||
       	       	        	type2.equals(courant.getChild("titre").getText()))
       	        		 )
       	           
       	         {
       	        	liste_data.add(courant.getChild("titre").getText());
       	         } 
       	      }
          }
       public  void traitement10(String selection,String se1 ,String TX){
          	charge_liste10(selection,se1,TX);
       		try {
      			 ((DefaultListModel<String>) jList_cherche1.getModel()).removeAllElements();
      		} catch (Exception e) {
      			// TODO: handle exception
      		}
       		
      		for (int i = 0; i < liste_data.size(); i++) {
      	    	model.addElement(liste_data.get(i));
      		}

      		jList_cherche1.setModel(model);

      		   liste_data.clear();
          }
       private void jComboBox_Categorie_Recherche_par_filtre1ActionPerformed(java.awt.event.ActionEvent evt) {                                                                          
           // TODO add your handling code here:
       } 
       private void jComboBox_Zone_Recherche_par_filtre1ActionPerformed(java.awt.event.ActionEvent evt) {                                                                     
           // TODO add your handling code here:
       }       
       private void jTextField_Autre_Recherche_par_filtre1ActionPerformed(java.awt.event.ActionEvent evt) {                                                                       
           // TODO add your handling code here:
       }  
       private void jButton_Zone_Recherche_par_filtre1ActionPerformed(java.awt.event.ActionEvent evt) {
    	traitement10(jTextField_Autre_Recherche_par_filtre1.getText(), (String)jComboBox_Categorie_Recherche_par_filtre1.getSelectedItem(), (String)jComboBox_Zone_Recherche_par_filtre1.getSelectedItem()); 
       }
       /**
        *  -------------------------FIN_Recherche_par_filtre
        * 
        */
       /**
        * --------------------------FIN_RECHERCHE_______________________________________
        */
       
       
      
       
       /**
      * --------------------------DEBUT_GENERE_PDF________________________
        */
       public void  Chrgement_client(){
           
       	  org.jdom.Document doc = null;
     	    Element rac;
       	   SAXBuilder sxb = new SAXBuilder();
       	      try
       	      {
       	     
       	         doc = sxb.build(new File("../XML_engine/src/Fiche_Client.xml"));
       	      }
       	      catch(Exception e){}


       	      rac = doc.getRootElement();
      	   List listEtudiants = rac.getChildren("client");

      	   Iterator i = listEtudiants.iterator();

      	      while(i.hasNext())
      	      {
      	     
      	    	   Element courant = (Element)i.next();
      	    
      	  	     Liste_client.add(courant.getChild("nom").getText());
      	  
      	      
      	      }

         }
       public void  Chrgement_objet(){
           
   	  	  org.jdom.Document doc = null;
   		    Element rac;
   	  	   SAXBuilder sxb = new SAXBuilder();
   	  	      try
   	  	      {
   	  	     
   	  	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
   	  	      }
   	  	      catch(Exception e){}


   	  	      rac = doc.getRootElement();
   	 	   List listEtudiants = rac.getChildren("objet");

   	 	   Iterator i = listEtudiants.iterator();

   	 	      while(i.hasNext())
   	 	      {
   	 	     
   	 	    	   Element courant = (Element)i.next();
   	 	    
   	 	    	  Liste_objet.add(courant.getChild("propritaire").getText());
   	 	  
   	 	      
   	 	      }

   	    }
       public String  Chrgement_numero(String prop){
           String numero="";
    	  	  org.jdom.Document doc = null;
    		    Element rac;
    	  	   SAXBuilder sxb = new SAXBuilder();
    	  	      try
    	  	      {
    	  	     
    	  	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
    	  	      }
    	  	      catch(Exception e){}


    	  	      rac = doc.getRootElement();
    	 	   List listEtudiants = rac.getChildren("objet");

    	 	   Iterator i = listEtudiants.iterator();

    	 	      while(i.hasNext())
    	 	      {
    	 	     
    	 	    	   Element courant = (Element)i.next();
    	 	    	   if(prop.equals(courant.getChild("propritaire").getText())){
    	 	    		   return courant.getChild("numero").getText();
    	 	    	   }
    	 	    
    	 	  
    	 	      
    	 	      }
    	 	      return "indisponible";

    	    }
       private void jButton1_genere_pdf1ActionPerformed(java.awt.event.ActionEvent evt) {                                                     
           // TODO add your handling code here:
       	try {
       		new Create_PDF().create1Pdf(RESULT,(String)jComboBox_Personnes_concernees4.getSelectedItem(),
					Chrgement_numero((String)jComboBox_Personnes_concernees4.getSelectedItem()),

       				
					(String)jComboBox_Personnes_concernees6.getSelectedItem(),
					Chrgement_numero((String)jComboBox_Personnes_concernees6.getSelectedItem()),
       				
       				
       				(String)jComboBox_Objetes_concernees4.getSelectedItem(),
       				(String)jComboBox_Objetes_concernees6.getSelectedItem(),
       				jTextArea_Clause_pdf1.getText(),
       				jTextArea_Mode_PDF1.getText(),
       				jTextArea_Description_PDF1.getText(),
       				jTextField_Montant_pdf1.getText(),
       				jTextField_Personne_PDF1.getText(),
       				jTextField_Taxe_pdf1.getText(),jTextField_date_pdf1.getText());
       		} catch (IOException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       			} catch (DocumentException e) {
       				// TODO Auto-generated catch block
       				e.printStackTrace();
       				} catch (SQLException e) {
       					// TODO Auto-generated catch block
       					e.printStackTrace();
       					}
       	Desktop desk = Desktop.getDesktop();
       	try {
       		desk.open(new File("../XML_engine/src/PDF/Contrat.pdf"));
       		} catch (IOException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       			}
       	} 
       /**
        * --------------------------FIN_GENERE_PDF________________________
        */
  
       
   /**
    * --------------------------------------DEBUT-Supprimer________________________
    */

       public void charge_liste1(String type){
          	System.out.println("aaaaaaa "+type);
        	  org.jdom.Document doc = null;
      	    Element rac;
        	   SAXBuilder sxb = new SAXBuilder();
        	      try
        	      {
        	         //On cree un nouveau document JDOM avec en argument le fichier XML
        	         //Le parsing est termine ;)
        	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
        	      }
        	      catch(Exception e){}
        	      //On initialise un nouvel element racine avec l'element racine du document.
        	      rac = doc.getRootElement();
        	      List listEtudiants = rac.getChildren("objet");
        	      Iterator i = listEtudiants.iterator();
       	      while(i.hasNext())
       	      {
       	   	   Element courant = (Element)i.next();
       	         if(type.equals(courant.getChild("categorie").getText())||
       	        		type.equals(courant.getChild("zone").getText()) ||
       	        		type.equals(courant.getChild("titre").getText())){
       	        	liste_data.add(courant.getChild("titre").getText());
       	         } 
       	      }
          }
       private void jList_SupprimerMouseClicked(java.awt.event.MouseEvent evt) {
    	   jTextArea_contre_Supprimer.setText("");
    	   jTextArea_echange_Supprimer.setText("");
          	load_information1();
          }
       public void load_information1(){
           	  org.jdom.Document doc = null;
         	    Element rac;
           	   SAXBuilder sxb = new SAXBuilder();
           	      try
           	      {
           	         //On cree un nouveau document JDOM avec en argument le fichier XML
           	         //Le parsing est termine ;)
           	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
           	      }
           	      catch(Exception e){}

           	      //On initialise un nouvel element racine avec l'element racine du document.
           	      rac = doc.getRootElement();
          	   List listEtudiants = rac.getChildren("objet");

          	   Iterator i = listEtudiants.iterator();

          	      while(i.hasNext())
          	      {
          	     
          	    	   Element courant = (Element)i.next();
          	    
          	      
          	         if(jList_Supprimer.getSelectedValue().equals(courant.getChild("titre").getText())){
          	        	jTextField_titre_Supprimer.setText(courant.getChild("titre").getText());
          	        	jTextField_couleur_Supprimer.setText(courant.getChild("couleur").getText());
          	        	jTextField_dimensionSupprimer.setText(courant.getChild("dimension").getText());
          	        	jTextArea_echange_Supprimer.append(courant.getChild("echange").getText());
          	        	jTextArea_contre_Supprimer.append(courant.getChild("contre").getText());
          	        	jTextField_telephone_Supprimer.setText(courant.getChild("numero").getText());
          	        	jLabel_image_Supprimer.setIcon(new javax.swing.ImageIcon("../XML_engine/src/Image/"+courant.getChild("url").getText())); // NOI18N

          	         }
          	      }
            }
       public  void traitement1(String selection){
          	charge_liste1(selection);
       		try {
      			 ((DefaultListModel<String>) jList_Supprimer.getModel()).removeAllElements();
      		} catch (Exception e) {
      			// TODO: handle exception
      		}
       		
      		for (int i = 0; i < liste_data.size(); i++) {
      	    	model.addElement(liste_data.get(i));
      		}

      		jList_Supprimer.setModel(model);

      		   liste_data.clear();
          }
       public  void remove_lignee(int lig) throws IOException {
     	    Vector monVector = new Vector();
         
            File f = new File("../XML_engine/src/Fiche_objet.xml"); 
            BufferedReader B = new BufferedReader(new FileReader(f)); 
            String ligne = B.readLine(); 
            while (ligne != null){ 
                monVector.addElement(ligne); 
                ligne = B.readLine(); 
          
            } 
            System.out.println(lig);
            monVector.removeElementAt(lig); 
            PrintWriter P = new PrintWriter (new FileWriter(f)); 
            for (int i = 0; i < monVector.size(); i++){ 
                P.println(monVector.get(i)); 
            } 
            P.close(); 
            monVector.clear();
    	}
       private void jButton_SupprimerActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
    	   
    	   JDialog.setDefaultLookAndFeelDecorated(true);
           int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
               JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
           
           if (response == JOptionPane.NO_OPTION) {
             System.out.println("No button clicked");
             
           } else if (response == JOptionPane.YES_OPTION) {
             System.out.println("Yes button clicked");
    	   
    	   int ligne =re_int((String)jList_Supprimer.getSelectedValue());
    	   remove_lignee(ligne+8);
    	   remove_lignee(ligne+7);
    	   remove_lignee(ligne+6);
    	   remove_lignee(ligne+5);
    	   remove_lignee(ligne+4);
    	   remove_lignee(ligne+3);
    	   remove_lignee(ligne+2);
    	   remove_lignee(ligne+1);
    	   remove_lignee(ligne);
    	   remove_lignee(ligne-1);
    	   remove_lignee(ligne-2);
    	   remove_lignee(ligne-3);
				jTextArea_contre_Supprimer.setText("");
				jTextArea_echange_Supprimer.setText("");
				jTextField_couleur_Supprimer.setText("");
				jTextField_dimensionSupprimer.setText("");
				jTextField_telephone_Supprimer.setText("");
				jTextField_titre_Supprimer.setText("");
   			
   				  int index = jList_Supprimer.getSelectedIndex();
   		    	  ((DefaultListModel) jList_Supprimer.getModel()).remove(index);
           } else if (response == JOptionPane.CLOSED_OPTION) {
               System.out.println("JOptionPane closed");
             }
       
    	   
       } 
       private void jButton_Rech_supActionPerformed(java.awt.event.ActionEvent evt) {                                                 
           // TODO add your handling code here:
          	traitement1(jTextField1.getText());
          	

    	   
    	   
       }
    	//fonction renvoi la ligne rechercher
   	   public  int re_int(String mot){
   		int compt=0;
   		String chaine="";
   		String fichier ="../XML_engine/src/Fiche_objet.xml";
   		//lecture du fichier texte	
   		try{
   			InputStream ips=new FileInputStream(fichier); 
   			InputStreamReader ipsr=new InputStreamReader(ips);
   			BufferedReader br=new BufferedReader(ipsr);
   			String ligne;
   			while ((ligne=br.readLine())!=null){
   			compt++;
   				if(ligne.equals("<titre>"+mot+"</titre>")){
   					return compt;
   				}
   			
   				chaine+=ligne+"\n";
   			}
   			br.close(); 
   		}		
   		catch (Exception e){
   			System.out.println(e.toString());
   		}
   	return 0;
   		
   		
   	}
       public int   cherche_titre(String titre){

    	   	  org.jdom.Document doc = null;
    	 	    Element rac;
    	   	   SAXBuilder sxb = new SAXBuilder();
    	   	      try
    	   	      {
    	   	         //On cr�e un nouveau document JDOM avec en argument le fichier XML
    	   	         //Le parsing est termin� ;)
    	   	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
    	   	      }
    	   	      catch(Exception e){}

    	   	      //On initialise un nouvel �l�ment racine avec l'�l�ment racine du document.
    	   	      rac = doc.getRootElement();
    	  	   List listEtudiants = rac.getChildren("itmes");

    	  	   Iterator i = listEtudiants.iterator();

    	  	      while(i.hasNext())
    	  	      {
    	  	    
    	  	    	   Element courant = (Element)i.next();
    	  	    	 numero++;
    	  	  
    	  	         if(titre.equals(courant.getChild("titre").getText())){

    	  	        	 return numero;
    	  	
    	  	         }
    	  	      }
    	return 0;
    	     }
       private void  verification_mel(String mel){
            
      	  org.jdom.Document doc = null;
    	    Element rac;
      	   SAXBuilder sxb = new SAXBuilder();
      	      try
      	      {
      	         //On cr�e un nouveau document JDOM avec en argument le fichier XML
      	         //Le parsing est termin� ;)
      	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
      	      }
      	      catch(Exception e){}

    
      	      rac = doc.getRootElement();
     	   List listEtudiants = rac.getChildren("objet");

     	   Iterator i = listEtudiants.iterator();

     	      while(i.hasNext())
     	      {
     	     
     	    	   Element courant = (Element)i.next();
     	    
     	      
     	         if(mel.equals(courant.getChild("propritaire").getText())){

     	liste_objet.add(courant.getChild("titre").getText());
     		cmpt++;
     	         }
     	      }

        }
       
   /**
    * ---------------------------------------FIN-Supprimer--------------------------
    * 
    */
       
     
       
   /**
   * --------------------------------------DEBUT-MODIFIER________________________
   */

        private void jButton_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {
        	
            // TODO add your handling code here:

        	JDialog.setDefaultLookAndFeelDecorated(true);
            int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.NO_OPTION) {
              System.out.println("No button clicked");
              
            } else if (response == JOptionPane.YES_OPTION) {
              System.out.println("Yes button clicked");
              
              add.append(c.client.getAdresse_mel(), jTextField_titre_Modifier1.getText(),
          			(String)jComboBox_Categorie_Modifier1.getSelectedItem(),
          			jTextField_couleur_Modifier1.getText(), 
          			jTextField_dimension_Modifier1.getText(), 
          			jTextArea_echange_Modifier1.getText(),
          			jTextArea_contre_Modifier1.getText(), 
          			(String)jComboBox_zone_Modifier1.getSelectedItem(), 
          			jTextField_telephone_Modifier1.getText(),url);

          			int ligne =re_int((String)jList_Modifier1.getSelectedValue());


          			try {


          			remove_lignee(ligne+8);
          			remove_lignee(ligne+7);
          			remove_lignee(ligne+6);
          			remove_lignee(ligne+5);
          			remove_lignee(ligne+4);
          			remove_lignee(ligne+3);
          			remove_lignee(ligne+2);
          			remove_lignee(ligne+1);
          			remove_lignee(ligne);
          			remove_lignee(ligne-1);
          			remove_lignee(ligne-2);
          			remove_lignee(ligne-3);


          			} catch (IOException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          			}
              
            } else if (response == JOptionPane.CLOSED_OPTION) {
              System.out.println("JOptionPane closed");
            }
        	
        	



        	
        } 
        private void jButton_RECH_MODActionPerformed(java.awt.event.ActionEvent evt) {                                                 
            // TODO add your handling code here:
          	traitement2(jTextField2.getText());

        	
        } 
        private void jList_Modifier1MouseClicked(java.awt.event.MouseEvent evt) {
        	jTextArea_contre_Modifier1.setText("");
    	   jTextArea_echange_Modifier1.setText("");
          	load_information2();
          }
        public void load_information2(){
           	  org.jdom.Document doc = null;
         	    Element rac;
           	   SAXBuilder sxb = new SAXBuilder();
           	      try
           	      {
           	         //On cree un nouveau document JDOM avec en argument le fichier XML
           	         //Le parsing est termine ;)
           	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
           	      }
           	      catch(Exception e){}

           	      //On initialise un nouvel element racine avec l'element racine du document.
           	      rac = doc.getRootElement();
          	   List listEtudiants = rac.getChildren("objet");

          	   Iterator i = listEtudiants.iterator();

          	      while(i.hasNext())
          	      {
          	     
          	    	   Element courant = (Element)i.next();
          	    
          	      
          	         if(jList_Modifier1.getSelectedValue().equals(courant.getChild("titre").getText())){
          	        	jTextField_titre_Modifier1.setText(courant.getChild("titre").getText());
          	        	jTextField_couleur_Modifier1.setText(courant.getChild("couleur").getText());
          	        	jTextField_dimension_Modifier1.setText(courant.getChild("dimension").getText());
          	        	jTextArea_echange_Modifier1.append(courant.getChild("echange").getText());
          	        	jTextArea_contre_Modifier1.append(courant.getChild("contre").getText());
          	        	jTextField_telephone_Modifier1.setText(courant.getChild("numero").getText());
          	        	jLabel_image_Modifier2.setIcon(new javax.swing.ImageIcon("../XML_engine/src/Image/"+courant.getChild("url").getText())); // NOI18N

          	         }
          	      }
            }
        public  void traitement2(String selection){
          	charge_liste1(selection);
       		try {
      			 ((DefaultListModel<String>) jList_Modifier1.getModel()).removeAllElements();
      		} catch (Exception e) {
      			// TODO: handle exception
      		}
       		
      		for (int i = 0; i < liste_data.size(); i++) {
      	    	model.addElement(liste_data.get(i));
      		}

      		jList_Modifier1.setModel(model);

      		   liste_data.clear();
          }
        public void charge_liste2(String type){
          	System.out.println("aaaaaaa "+type);
        	  org.jdom.Document doc = null;
      	    Element rac;
        	   SAXBuilder sxb = new SAXBuilder();
        	      try
        	      {
        	         //On cree un nouveau document JDOM avec en argument le fichier XML
        	         //Le parsing est termine ;)
        	         doc = sxb.build(new File("../XML_engine/src/Fiche_objet.xml"));
        	      }
        	      catch(Exception e){}
        	      //On initialise un nouvel element racine avec l'element racine du document.
        	      rac = doc.getRootElement();
        	      List listEtudiants = rac.getChildren("objet");
        	      Iterator i = listEtudiants.iterator();
       	      while(i.hasNext())
       	      {
       	   	   Element courant = (Element)i.next();
       	         if(type.equals(courant.getChild("categorie").getText())||
       	        		type.equals(courant.getChild("zone").getText()) ||
       	        		type.equals(courant.getChild("titre").getText())){
       	        	liste_data.add(courant.getChild("titre").getText());
       	         } 
       	      }
          }

 
   /**
   * ---------------------------------------FIN-Modifier--------------------------
   * 
   */

          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Ajouter_image_Ajouter = new javax.swing.JButton();
        jButton_valider_Ajouter = new javax.swing.JButton();
        jTextField_telephone_Ajouter = new javax.swing.JTextField();
        jTextField_dimension_Ajouter = new javax.swing.JTextField();
        jComboBox_zone_Ajouter = new javax.swing.JComboBox();
        jTextField_titre_Ajouter = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_contre_Ajouter = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_echange_Ajouter = new javax.swing.JTextArea();
        jTextField_couleur_Ajouter = new javax.swing.JTextField();
        jComboBox_Categorie_Ajouter = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jTextField_telephone_Modifier1 = new javax.swing.JTextField();
        jTextField_dimension_Modifier1 = new javax.swing.JTextField();
        jComboBox_zone_Modifier1 = new javax.swing.JComboBox();
        jTextField_titre_Modifier1 = new javax.swing.JTextField();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextArea_contre_Modifier1 = new javax.swing.JTextArea();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextArea_echange_Modifier1 = new javax.swing.JTextArea();
        jTextField_couleur_Modifier1 = new javax.swing.JTextField();
        jComboBox_Categorie_Modifier1 = new javax.swing.JComboBox();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jList_Modifier1 = new javax.swing.JList();
        jLabel_image_Modifier2 = new javax.swing.JLabel();
        jLabel_image_Modif2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton_RECH_MOD = new javax.swing.JButton();
        jButton_Modifier1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField_telephone_Supprimer = new javax.swing.JTextField();
        jTextField_dimensionSupprimer = new javax.swing.JTextField();
        jComboBox_zone_Supprimer = new javax.swing.JComboBox();
        jTextField_titre_Supprimer = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_contre_Supprimer = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea_echange_Supprimer = new javax.swing.JTextArea();
        jTextField_couleur_Supprimer = new javax.swing.JTextField();
        jComboBox_Categorie_Supprimer = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList_Supprimer = new javax.swing.JList();
        jLabel_image_Supprimer = new javax.swing.JLabel();
        jLabel_image_Supp = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel_Recherche_sup = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton_Rech_sup = new javax.swing.JButton();
        jButton_Supprimer = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jRadioButton_categorie_Recherche1 = new javax.swing.JRadioButton();
        jRadioButton_Zone_Recherche1 = new javax.swing.JRadioButton();
        jTextField_Autre_Recherche1 = new javax.swing.JTextField();
        jComboBox_Categorie_Recherche1 = new javax.swing.JComboBox();
        jComboBox_Zone_Recherche1 = new javax.swing.JComboBox();
        jButton_recherche_simple1 = new javax.swing.JButton();
        jRadioButton_Autre_Recherche1 = new javax.swing.JRadioButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextField_Autre_Recherche_par_filtre1 = new javax.swing.JTextField();
        jComboBox_Categorie_Recherche_par_filtre1 = new javax.swing.JComboBox();
        jComboBox_Zone_Recherche_par_filtre1 = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jButton_Zone_Recherche_par_filtre1 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel_dimension_Recherche1 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel_couleur_Recherche1 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel_Telephone_Recherche1 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea_Echange_Recherche1 = new javax.swing.JTextArea();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea_Contre_Recherche1 = new javax.swing.JTextArea();
        jLabel64 = new javax.swing.JLabel();
        jLabel_image_rechreche1 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jList_cherche1 = new javax.swing.JList();
        jLabel65 = new javax.swing.JLabel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jComboBox_Personnes_concernees4 = new javax.swing.JComboBox();
        jComboBox_Personnes_concernees6 = new javax.swing.JComboBox();
        jLabel66 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jComboBox_Objetes_concernees4 = new javax.swing.JComboBox();
        jComboBox_Objetes_concernees6 = new javax.swing.JComboBox();
        jLabel67 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jTextField_Montant_pdf1 = new javax.swing.JTextField();
        jTextField_Personne_PDF1 = new javax.swing.JTextField();
        jTextField_Taxe_pdf1 = new javax.swing.JTextField();
        jTextField_date_pdf1 = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea_Clause_pdf1 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea_Mode_PDF1 = new javax.swing.JTextArea();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea_Description_PDF1 = new javax.swing.JTextArea();
        jPanel26 = new javax.swing.JPanel();
        jButton1_genere_pdf1 = new javax.swing.JButton();
        FIN1 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 204, 0)));

        jLabel1.setText("Titre");

        jLabel2.setText("Couleur");

        jLabel3.setText("Categorie");

        jLabel4.setText("Echange");

        jLabel5.setText("Zone d'echange");

        jLabel6.setText("Contre");

        jLabel7.setText("Telephone");

        jLabel8.setText("Dimension");

        Ajouter_image_Ajouter.setText("Image");
        Ajouter_image_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ajouter_image_AjouterActionPerformed(evt);
            }
        });

        jButton_valider_Ajouter.setText("valider");
        jButton_valider_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_valider_AjouterActionPerformed(evt);
            }
        });

        jTextField_telephone_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_telephone_AjouterActionPerformed(evt);
            }
        });

        jTextField_dimension_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_dimension_AjouterActionPerformed(evt);
            }
        });

        jComboBox_zone_Ajouter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alsace", "Aquitaine", "Auvergne", "Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "Franche-Comté", "Île-de-France", "Languedoc-Roussillon", "Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Basse-Normandie", "Pays de la Loire", "Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes", " " }));
        jComboBox_zone_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_zone_AjouterActionPerformed(evt);
            }
        });

        jTextField_titre_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_titre_AjouterActionPerformed(evt);
            }
        });

        jTextArea_contre_Ajouter.setColumns(20);
        jTextArea_contre_Ajouter.setRows(5);
        jScrollPane1.setViewportView(jTextArea_contre_Ajouter);

        jTextArea_echange_Ajouter.setColumns(20);
        jTextArea_echange_Ajouter.setRows(5);
        jScrollPane2.setViewportView(jTextArea_echange_Ajouter);

        jTextField_couleur_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_couleur_AjouterActionPerformed(evt);
            }
        });

        jComboBox_Categorie_Ajouter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Categorie_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Categorie_AjouterActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Zapfino", 3, 36)); // NOI18N
        jLabel54.setText("Ajouter");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Ajouter_image_Ajouter)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_couleur_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_dimension_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel8)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox_zone_Ajouter, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox_Categorie_Ajouter, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_titre_Ajouter, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addComponent(jTextField_telephone_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jButton_valider_Ajouter)
                                .addGap(145, 145, 145)))))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_telephone_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_titre_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_Categorie_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox_zone_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_couleur_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_dimension_Ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ajouter_image_Ajouter)
                    .addComponent(jButton_valider_Ajouter))
                .addGap(16, 16, 16))
        );

        jTabbedPane5.addTab("Ajouter", jPanel7);

        jPanel27.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 153, 51)));

        jLabel74.setText("Titre");

        jLabel75.setText("Couleur");

        jLabel76.setText("Categorie");

        jLabel77.setText("Echange");

        jLabel78.setText("Zone d'echange");

        jLabel79.setText("Contre");

        jLabel80.setText("Telephone");

        jLabel81.setText("Dimension");

        jTextField_telephone_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_telephone_Modifier1ActionPerformed(evt);
            }
        });

        jTextField_dimension_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_dimension_Modifier1ActionPerformed(evt);
            }
        });

        jComboBox_zone_Modifier1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alsace", "Aquitaine", "Auvergne", "Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "Franche-Comté", "Île-de-France", "Languedoc-Roussillon", "Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Basse-Normandie", "Pays de la Loire", "Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes", " " }));
        jComboBox_zone_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_zone_Modifier1ActionPerformed(evt);
            }
        });

        jTextField_titre_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_titre_Modifier1ActionPerformed(evt);
            }
        });

        jTextArea_contre_Modifier1.setColumns(20);
        jTextArea_contre_Modifier1.setRows(5);
        jScrollPane21.setViewportView(jTextArea_contre_Modifier1);

        jTextArea_echange_Modifier1.setColumns(20);
        jTextArea_echange_Modifier1.setRows(5);
        jScrollPane22.setViewportView(jTextArea_echange_Modifier1);

        jTextField_couleur_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_couleur_Modifier1ActionPerformed(evt);
            }
        });

        jComboBox_Categorie_Modifier1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Categorie_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Categorie_Modifier1ActionPerformed(evt);
            }
        });

        jLabel82.setText("Liste Objets");

        jLabel83.setFont(new java.awt.Font("Zapfino", 3, 36)); // NOI18N
        jLabel83.setText("Modifier");

        jScrollPane23.setViewportView(jList_Modifier1);

        jLabel_image_Modif2.setText("Image:");

        jLabel9.setText("Objet");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton_RECH_MOD.setText("RECHERCHE");
        jButton_RECH_MOD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RECH_MODActionPerformed(evt);
            }
        });
        
        jList_Modifier1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	jList_Modifier1MouseClicked(evt);
            }
        });
        

        jButton_Modifier1.setText("MODIFIER");
        jButton_Modifier1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Modifier1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jButton_RECH_MOD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Modifier1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_RECH_MOD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Modifier1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel27Layout.createSequentialGroup()
                                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel80)
                                            .addComponent(jTextField_dimension_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel81)
                                            .addComponent(jTextField_couleur_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel75)
                                            .addComponent(jLabel78)
                                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jComboBox_zone_Modifier1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                                .addComponent(jComboBox_Categorie_Modifier1, javax.swing.GroupLayout.Alignment.LEADING, 0, 208, Short.MAX_VALUE))
                                            .addComponent(jLabel76)
                                            .addComponent(jTextField_titre_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel74))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE))
                                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jTextField_telephone_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79))
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel77)))
                            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel_image_Modifier2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel27Layout.createSequentialGroup()
                                    .addComponent(jLabel_image_Modif2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(182, 182, 182))))
                        .addGap(504, 504, 504))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addGap(143, 143, 143)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel82)))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel79)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_image_Modif2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_image_Modifier2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_titre_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox_Categorie_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_zone_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_couleur_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel81)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_dimension_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel80)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_telephone_Modifier1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)))
                        .addContainerGap())))
        );

        jTabbedPane5.addTab("Modifier", jPanel27);

        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 51, 51)));

        jLabel17.setText("Titre");

        jLabel18.setText("Couleur");

        jLabel19.setText("Categorie");

        jLabel20.setText("Echange");

        jLabel21.setText("Zone d'echange");

        jLabel22.setText("Contre");

        jLabel23.setText("Telephone");

        jLabel24.setText("Dimension");

        jTextField_telephone_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_telephone_SupprimerActionPerformed(evt);
            }
        });

        jTextField_dimensionSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_dimensionSupprimerActionPerformed(evt);
            }
        });

        jComboBox_zone_Supprimer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alsace", "Aquitaine", "Auvergne", "Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "Franche-Comté", "Île-de-France", "Languedoc-Roussillon", "Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Basse-Normandie", "Pays de la Loire", "Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes", " " }));
        jComboBox_zone_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_zone_SupprimerActionPerformed(evt);
            }
        });

        jTextField_titre_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_titre_SupprimerActionPerformed(evt);
            }
        });

        jTextArea_contre_Supprimer.setColumns(20);
        jTextArea_contre_Supprimer.setRows(5);
        jScrollPane5.setViewportView(jTextArea_contre_Supprimer);

        jTextArea_echange_Supprimer.setColumns(20);
        jTextArea_echange_Supprimer.setRows(5);
        jScrollPane6.setViewportView(jTextArea_echange_Supprimer);

        jTextField_couleur_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_couleur_SupprimerActionPerformed(evt);
            }
        });

        jComboBox_Categorie_Supprimer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Categorie_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Categorie_SupprimerActionPerformed(evt);
            }
        });

        jLabel34.setText("Liste Objets");

        jLabel56.setFont(new java.awt.Font("Zapfino", 3, 36)); // NOI18N
        jLabel56.setText("Supprimer");

        jScrollPane11.setViewportView(jList_Supprimer);

        jLabel_image_Supp.setText("Image:");

        jLabel_Recherche_sup.setText("Objet");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton_Rech_sup.setText("Recherche");
        jButton_Rech_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Rech_supActionPerformed(evt);
            }
        });
        
        jList_Supprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	jList_SupprimerMouseClicked(evt);
            }
        });
        

        jButton_Supprimer.setText("Supprimer");
        jButton_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton_SupprimerActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Rech_sup)
                            .addComponent(jButton_Supprimer)
                            .addComponent(jLabel_Recherche_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel_Recherche_sup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Rech_sup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_couleur_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_dimensionSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_zone_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel18)
                            .addComponent(jLabel24)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(jTextField_titre_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_telephone_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(jComboBox_Categorie_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel22)
                                .addComponent(jLabel_image_Supp, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel_image_Supprimer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel20)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel_image_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_image_Supp)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel34)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_titre_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_telephone_Supprimer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Categorie_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox_zone_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_couleur_Supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_dimensionSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel23)
                        .addGap(34, 34, 34)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Supprimer", jPanel11);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 717, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Mes Objets", jPanel2);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel38.setForeground(new java.awt.Color(0, 204, 0));
        jLabel38.setText("Rechercehe Simple");

        jRadioButton_categorie_Recherche1.setText("Categorie");
        jRadioButton_categorie_Recherche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_categorie_Recherche1ActionPerformed(evt);
            }
        });

        jRadioButton_Zone_Recherche1.setText("Zone ");
        jRadioButton_Zone_Recherche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Zone_Recherche1ActionPerformed(evt);
            }
        });

        jTextField_Autre_Recherche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Autre_Recherche1ActionPerformed(evt);
            }
        });

        jComboBox_Categorie_Recherche1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Categorie_Recherche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Categorie_Recherche1ActionPerformed(evt);
            }
        });

        jComboBox_Zone_Recherche1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alsace", "Aquitaine", "Auvergne", "Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "Franche-Comté", "Île-de-France", "Languedoc-Roussillon", "Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Basse-Normandie", "Pays de la Loire", "Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes", " " }));
        jComboBox_Zone_Recherche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Zone_Recherche1ActionPerformed(evt);
            }
        });

        jButton_recherche_simple1.setText("Recherche");
        jButton_recherche_simple1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_recherche_simple1ActionPerformed(evt);
            }
        });
        jList_cherche1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_cherche1MouseClicked(evt);
            }
        });

        jRadioButton_Autre_Recherche1.setText("Autre");
        jRadioButton_Autre_Recherche1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Autre_Recherche1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jRadioButton_Autre_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_Zone_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_categorie_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Autre_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox_Zone_Recherche1, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                .addComponent(jComboBox_Categorie_Recherche1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(213, 213, 213))))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jButton_recherche_simple1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_categorie_Recherche1)
                    .addComponent(jComboBox_Categorie_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Zone_Recherche1)
                    .addComponent(jComboBox_Zone_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Autre_Recherche1)
                    .addComponent(jTextField_Autre_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_recherche_simple1)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel45.setForeground(new java.awt.Color(255, 0, 0));
        jLabel45.setText("Rechercehe par filtre");

        jLabel47.setText("Autre");

        jTextField_Autre_Recherche_par_filtre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Autre_Recherche_par_filtre1ActionPerformed(evt);
            }
        });

        jComboBox_Categorie_Recherche_par_filtre1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Categorie_Recherche_par_filtre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Categorie_Recherche_par_filtre1ActionPerformed(evt);
            }
        });

        jComboBox_Zone_Recherche_par_filtre1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alsace", "Aquitaine", "Auvergne", "Bourgogne", "Bretagne", "Centre", "Champagne-Ardenne", "Corse", "Franche-Comté", "Île-de-France", "Languedoc-Roussillon", "Limousin", "Lorraine", "Midi-Pyrénées", "Nord-Pas-de-Calais", "Basse-Normandie", "Pays de la Loire", "Picardie", "Poitou-Charentes", "Provence-Alpes-Côte d'Azur", "Rhône-Alpes" }));
        jComboBox_Zone_Recherche_par_filtre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Zone_Recherche_par_filtre1ActionPerformed(evt);
            }
        });

        jLabel49.setText("Categorie");

        jLabel57.setText("Zone");

        jButton_Zone_Recherche_par_filtre1.setText("Rechrche");
        jButton_Zone_Recherche_par_filtre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Zone_Recherche_par_filtre1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jTextField_Autre_Recherche_par_filtre1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Zone_Recherche_par_filtre1))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_Categorie_Recherche_par_filtre1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Zone_Recherche_par_filtre1, 0, 1, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jComboBox_Categorie_Recherche_par_filtre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jComboBox_Zone_Recherche_par_filtre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Autre_Recherche_par_filtre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Zone_Recherche_par_filtre1))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        jLabel58.setText("Liste Objets Trouve");

        jLabel59.setText("Dimension :");

        jLabel_dimension_Recherche1.setText("Dimension_info");

        jLabel60.setText("Couleur : ");

        jLabel_couleur_Recherche1.setText("Couleur_info");

        jLabel61.setText("Contact :");

        jLabel_Telephone_Recherche1.setText("Contact_info");

        jLabel62.setText("Echange");

        jTextArea_Echange_Recherche1.setColumns(20);
        jTextArea_Echange_Recherche1.setRows(5);
        jScrollPane15.setViewportView(jTextArea_Echange_Recherche1);

        jLabel63.setText("Contre: ");

        jTextArea_Contre_Recherche1.setColumns(20);
        jTextArea_Contre_Recherche1.setRows(5);
        jScrollPane16.setViewportView(jTextArea_Contre_Recherche1);

        jLabel64.setText("Image:");

        jScrollPane12.setViewportView(jList_cherche1);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel_image_rechreche1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane16)
                        .addComponent(jScrollPane15)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel62)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel63)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(182, 182, 182)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_dimension_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_couleur_Recherche1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_Telephone_Recherche1))))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(jLabel_dimension_Recherche1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(jLabel_couleur_Recherche1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(jLabel_Telephone_Recherche1))))
                .addGap(18, 18, 18)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_image_rechreche1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel65.setFont(new java.awt.Font("Zapfino", 3, 36)); // NOI18N
        jLabel65.setText("Recherche");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );

        jTabbedPane3.addTab("Recherche", jPanel15);

        jTabbedPane7.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 204)));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox_Personnes_concernees4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Personnes_concernees4ActionPerformed(evt);
            }
        });

        jComboBox_Personnes_concernees6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Personnes_concernees6ActionPerformed(evt);
            }
        });

        jLabel66.setText("Personnes concernees ");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_Personnes_concernees4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_Personnes_concernees6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox_Personnes_concernees4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox_Personnes_concernees6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox_Objetes_concernees4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Objetes_concernees4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Objetes_concernees4ActionPerformed(evt);
            }
        });

        jComboBox_Objetes_concernees6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Informatique", "Materiel", "Immobilier", "Maison", "Telephone", "Voiture", "Moto" }));
        jComboBox_Objetes_concernees6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Objetes_concernees6ActionPerformed(evt);
            }
        });

        jLabel67.setText("Objetes concernees");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_Objetes_concernees6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Objetes_concernees4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel67)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel67)
                .addGap(18, 18, 18)
                .addComponent(jComboBox_Objetes_concernees4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox_Objetes_concernees6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        jLabel33.setText("Montant de la taxe");

        jLabel68.setText("Personne qui devra payer");

        jLabel69.setText("Taxe due a");

        jLabel70.setText("Date limite de reception");

        jLabel71.setText("Clause de Rupture");

        jLabel72.setText("Mode de Resolution");

        jLabel73.setText("Description de la Clause Taxation");

        jTextField_Montant_pdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Montant_pdf1ActionPerformed(evt);
            }
        });

        jTextField_Personne_PDF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Personne_PDF1ActionPerformed(evt);
            }
        });

        jTextField_Taxe_pdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Taxe_pdf1ActionPerformed(evt);
            }
        });

        jTextField_date_pdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_date_pdf1ActionPerformed(evt);
            }
        });

        jTextArea_Clause_pdf1.setColumns(20);
        jTextArea_Clause_pdf1.setRows(5);
        jScrollPane18.setViewportView(jTextArea_Clause_pdf1);

        jTextArea_Mode_PDF1.setColumns(20);
        jTextArea_Mode_PDF1.setRows(5);
        jScrollPane19.setViewportView(jTextArea_Mode_PDF1);

        jTextArea_Description_PDF1.setColumns(20);
        jTextArea_Description_PDF1.setRows(5);
        jScrollPane20.setViewportView(jTextArea_Description_PDF1);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel25Layout.createSequentialGroup()
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField_Personne_PDF1)
                                        .addComponent(jTextField_Montant_pdf1)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                    .addComponent(jLabel68))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_date_pdf1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70)
                                    .addComponent(jTextField_Taxe_pdf1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel69))
                .addGap(9, 9, 9)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Montant_pdf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Taxe_pdf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jLabel70))
                .addGap(22, 22, 22)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Personne_PDF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_date_pdf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1_genere_pdf1.setText("Genere PDF");
        jButton1_genere_pdf1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_genere_pdf1ActionPerformed(evt);
            }
        });

        FIN1.setText("FIN");
        FIN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FIN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1_genere_pdf1)
                    .addComponent(FIN1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1_genere_pdf1)
                .addGap(18, 18, 18)
                .addComponent(FIN1))
        );

        jLabel84.setFont(new java.awt.Font("Zapfino", 3, 36)); // NOI18N
        jLabel84.setText("Contrat");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 100, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane7.addTab("Contrat", jPanel22);

        jTabbedPane3.addTab("Generer PDF", jTabbedPane7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 748, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jTabbedPane3.getAccessibleContext().setAccessibleName("Mes Objets");

        pack();
    }// </editor-fold>                        

    private void FIN1ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    
                                                   

    private void jTextField_date_pdf1ActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void jTextField_Taxe_pdf1ActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }                                                    

    private void jTextField_Personne_PDF1ActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        // TODO add your handling code here:
    }                                                        

    private void jTextField_Montant_pdf1ActionPerformed(java.awt.event.ActionEvent evt) {                                                        
        // TODO add your handling code here:
    }                                                       

    private void jComboBox_Objetes_concernees6ActionPerformed(java.awt.event.ActionEvent evt) {                                                              
        // TODO add your handling code here:
    }                                                             

    private void jComboBox_Objetes_concernees4ActionPerformed(java.awt.event.ActionEvent evt) {                                                              
        // TODO add your handling code here:
    }                                                             

    private void jComboBox_Personnes_concernees6ActionPerformed(java.awt.event.ActionEvent evt) {                                                                
        // TODO add your handling code here:
    }                                                               

    private void jComboBox_Personnes_concernees4ActionPerformed(java.awt.event.ActionEvent evt) {                                                                
        // TODO add your handling code here:
    }                                                               

    private void jComboBox_Categorie_AjouterActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        // TODO add your handling code here:
    }                                                           

    private void jTextField_couleur_AjouterActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        // TODO add your handling code here:
    }                                                          

    private void jTextField_titre_AjouterActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        // TODO add your handling code here:
    }                                                        

    private void jComboBox_zone_AjouterActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                      

    private void jTextField_dimension_AjouterActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        // TODO add your handling code here:
    }                                                            

    private void jTextField_telephone_AjouterActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        // TODO add your handling code here:
    }                                                            

                                                      

    private void jComboBox_Categorie_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {                                                              
        // TODO add your handling code here:
    }                                                             

    private void jTextField_couleur_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        // TODO add your handling code here:
    }                                                            

    private void jTextField_titre_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        // TODO add your handling code here:
    }                                                          

    private void jComboBox_zone_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        // TODO add your handling code here:
    }                                                        

    private void jTextField_dimensionSupprimerActionPerformed(java.awt.event.ActionEvent evt) {                                                              
        // TODO add your handling code here:
    }                                                             

    private void jTextField_telephone_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {                                                               
        // TODO add your handling code here:
    }                                                              
                                                 
                                                

    private void jComboBox_zone_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        // TODO add your handling code here:
    }                                                        

    private void jComboBox_Categorie_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {                                                              
        // TODO add your handling code here:
    }                                                             

    private void jTextField_titre_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        // TODO add your handling code here:
    }                                                          

    private void jTextField_couleur_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        // TODO add your handling code here:
    }                                                            

    private void jTextField_dimension_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {                                                               
        // TODO add your handling code here:
    }                                                              

    private void jTextField_telephone_Modifier1ActionPerformed(java.awt.event.ActionEvent evt) {                                                               
        // TODO add your handling code here:
    }                                                              

                                                

                                               

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton Ajouter_image_Ajouter;
    private javax.swing.JButton FIN1;
    private javax.swing.JButton jButton1_genere_pdf1;
    private javax.swing.JButton jButton_Modifier1;
    private javax.swing.JButton jButton_RECH_MOD;
    private javax.swing.JButton jButton_Rech_sup;
    private javax.swing.JButton jButton_Supprimer;
    private javax.swing.JButton jButton_Zone_Recherche_par_filtre1;
    private javax.swing.JButton jButton_recherche_simple1;
    private javax.swing.JButton jButton_valider_Ajouter;
    private javax.swing.JComboBox jComboBox_Categorie_Ajouter;
    private javax.swing.JComboBox jComboBox_Categorie_Modifier1;
    private javax.swing.JComboBox jComboBox_Categorie_Recherche1;
    private javax.swing.JComboBox jComboBox_Categorie_Recherche_par_filtre1;
    private javax.swing.JComboBox jComboBox_Categorie_Supprimer;
    private javax.swing.JComboBox jComboBox_Objetes_concernees4;
    private javax.swing.JComboBox jComboBox_Objetes_concernees6;
    private javax.swing.JComboBox jComboBox_Personnes_concernees4;
    private javax.swing.JComboBox jComboBox_Personnes_concernees6;
    private javax.swing.JComboBox jComboBox_Zone_Recherche1;
    private javax.swing.JComboBox jComboBox_Zone_Recherche_par_filtre1;
    private javax.swing.JComboBox jComboBox_zone_Ajouter;
    private javax.swing.JComboBox jComboBox_zone_Modifier1;
    private javax.swing.JComboBox jComboBox_zone_Supprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Recherche_sup;
    private javax.swing.JLabel jLabel_Telephone_Recherche1;
    private javax.swing.JLabel jLabel_couleur_Recherche1;
    private javax.swing.JLabel jLabel_dimension_Recherche1;
    private javax.swing.JLabel jLabel_image_Modif2;
    private javax.swing.JLabel jLabel_image_Modifier2;
    private javax.swing.JLabel jLabel_image_Supp;
    private javax.swing.JLabel jLabel_image_Supprimer;
    private javax.swing.JLabel jLabel_image_rechreche1;
    private javax.swing.JList jList_Modifier1;
    private javax.swing.JList jList_Supprimer;
    private javax.swing.JList jList_cherche1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton_Autre_Recherche1;
    private javax.swing.JRadioButton jRadioButton_Zone_Recherche1;
    private javax.swing.JRadioButton jRadioButton_categorie_Recherche1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTextArea jTextArea_Clause_pdf1;
    private javax.swing.JTextArea jTextArea_Contre_Recherche1;
    private javax.swing.JTextArea jTextArea_Description_PDF1;
    private javax.swing.JTextArea jTextArea_Echange_Recherche1;
    private javax.swing.JTextArea jTextArea_Mode_PDF1;
    private javax.swing.JTextArea jTextArea_contre_Ajouter;
    private javax.swing.JTextArea jTextArea_contre_Modifier1;
    private javax.swing.JTextArea jTextArea_contre_Supprimer;
    private javax.swing.JTextArea jTextArea_echange_Ajouter;
    private javax.swing.JTextArea jTextArea_echange_Modifier1;
    private javax.swing.JTextArea jTextArea_echange_Supprimer;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField_Autre_Recherche1;
    private javax.swing.JTextField jTextField_Autre_Recherche_par_filtre1;
    private javax.swing.JTextField jTextField_Montant_pdf1;
    private javax.swing.JTextField jTextField_Personne_PDF1;
    private javax.swing.JTextField jTextField_Taxe_pdf1;
    private javax.swing.JTextField jTextField_couleur_Ajouter;
    private javax.swing.JTextField jTextField_couleur_Modifier1;
    private javax.swing.JTextField jTextField_couleur_Supprimer;
    private javax.swing.JTextField jTextField_date_pdf1;
    private javax.swing.JTextField jTextField_dimensionSupprimer;
    private javax.swing.JTextField jTextField_dimension_Ajouter;
    private javax.swing.JTextField jTextField_dimension_Modifier1;
    private javax.swing.JTextField jTextField_telephone_Ajouter;
    private javax.swing.JTextField jTextField_telephone_Modifier1;
    private javax.swing.JTextField jTextField_telephone_Supprimer;
    private javax.swing.JTextField jTextField_titre_Ajouter;
    private javax.swing.JTextField jTextField_titre_Modifier1;
    private javax.swing.JTextField jTextField_titre_Supprimer;
    // End of variables declaration                   
}
