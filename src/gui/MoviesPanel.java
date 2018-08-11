/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Genre;
import domain.Movie;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author jodus
 */
public class MoviesPanel extends javax.swing.JPanel {

    private MoviesPanelViewModel viewModel;

    /**
     * Creates new form MoviesPanel
     */
    public MoviesPanel() {
        initComponents();
        
        jMoviesList.setModel(new DefaultListModel<>());
        this.viewModel = new MoviesPanelViewModel();
        update();
    }
    
    public void update() {
        viewModel.fetchMovieList();
        viewModel.fetchGenreList();
        
        DefaultListModel moviesList = (DefaultListModel) jMoviesList.getModel();
        moviesList.removeAllElements();
        for (Movie movie: viewModel.getMovieList()) {
            moviesList.addElement(movie.title);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jMoviesList = new javax.swing.JList<>();
        jAddBtn = new javax.swing.JButton();
        jDeleteBtn = new javax.swing.JButton();

        jMoviesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jMoviesList);

        jAddBtn.setText("Add");
        jAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddBtnActionPerformed(evt);
            }
        });

        jDeleteBtn.setText("Delete");
        jDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAddBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDeleteBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jAddBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDeleteBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddBtnActionPerformed
        String name = JOptionPane.showInputDialog(this, "Add movie");
        if (name == null || name.length() == 0) {
            return;
        }
        
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
        List<Genre> genres = viewModel.getGenreList();
        for (Genre g: genres) {
            boxModel.addElement(g.name);
        }

        JComboBox optionList = new JComboBox(boxModel);
        JOptionPane.showMessageDialog(this, optionList, "Choose the genre", JOptionPane.QUESTION_MESSAGE);
        int genreIndex = optionList.getSelectedIndex();
        if (genreIndex == -1) {
            return;
        }
        viewModel.handleMovieCreate(name, genres.get(genreIndex));
        update();
    }//GEN-LAST:event_jAddBtnActionPerformed

    private void jDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteBtnActionPerformed
        int movieIndex = jMoviesList.getSelectedIndex();
        if (movieIndex == -1) {
            return;
        }
        Movie m = viewModel.getMovieList().get(movieIndex);
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the movie: " + m.title);
        if (result == JOptionPane.OK_OPTION) {
            viewModel.handleDelete(m);
            update();
        }
    }//GEN-LAST:event_jDeleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddBtn;
    private javax.swing.JButton jDeleteBtn;
    private javax.swing.JList<String> jMoviesList;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}