package Presentacion;

import Controlador.ControlNotasAlumno;
import Controlador.ControlSesionAlumno;
import Entidad.Curso;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aless
 */
public class panel_NotasAlumno extends javax.swing.JPanel {

    ControlNotasAlumno controlNotas = new ControlNotasAlumno();
    DefaultTableModel tmodel;

    public panel_NotasAlumno() {
        initComponents();
    }

    public panel_NotasAlumno(ControlSesionAlumno controlSesion) {
        controlNotas.setAlumno(controlSesion.getAlumno());
        controlNotas.setAlumno(controlSesion.getAlumno());
        //Listar Cursos del alumno
        controlNotas.obtenerCursosAlumno();
        initComponents();
        tmodel = (DefaultTableModel) tbCursos.getModel();
        cargarListaCursos();
        mostrarAllCursos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCursos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPromedioNotas = new javax.swing.JTextField();
        txtProfesorAcargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPromedioPractica = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPromedioExamen = new javax.swing.JTextField();
        btnMostrar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbCursos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "NOTAS", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        tbCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Practica 1", "Practica 2", "Practica 3", "Examen 1", "Examen 2", "Examen 3", "Curso"
            }
        ));
        tbCursos.setToolTipText("");
        jScrollPane1.setViewportView(tbCursos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 217, 600, 390));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("PROFESOR ACARGO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, -1, -1));

        cbCursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR" }));
        cbCursos.setName("cbCursos"); // NOI18N
        cbCursos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursosItemStateChanged(evt);
            }
        });
        jPanel1.add(cbCursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 30));

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("SELECIONE EL CURSO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 160, 20));

        txtPromedioNotas.setEnabled(false);
        jPanel1.add(txtPromedioNotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, 30));

        txtProfesorAcargo.setEnabled(false);
        jPanel1.add(txtProfesorAcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 140, 30));

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("PROMEDIO CURSO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("PROMEDIO DE PRACTICA (40%)");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        txtPromedioPractica.setEnabled(false);
        jPanel1.add(txtPromedioPractica, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 120, 30));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("PROMEDIO DE EXAMEN (60%)");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));

        txtPromedioExamen.setEnabled(false);
        jPanel1.add(txtPromedioExamen, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 120, 30));

        btnMostrar.setText("BUSCAR NOTAS");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 140, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbCursosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursosItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursosItemStateChanged

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        // TODO add your handling code here:
        String itemSeleccionado = cbCursos.getSelectedItem().toString();
        if(cbCursos.getSelectedIndex()==0)
            JOptionPane.showInternalMessageDialog(null, "Seleccione un curso ");
        controlNotas.obtenerCursosAlumno();
        for (var curso : controlNotas.getCursosAlumno()) {
            if (curso.getNombreCurso().equals(itemSeleccionado)) {
                System.out.println(curso);
                controlNotas.setCurso(curso);
                controlNotas.obtenerNotasAlumnoById();
                mostrarNotasCursoById(curso);
                mostrarProfesor(curso);
                mostrarPromedioPractica();
                mostrarPromedioExamen();
                mostrarPromedioCurso();
            }
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void mostrarAllCursos(){
        controlNotas.obtenerAllNotasCursos();
        for(var ob: controlNotas.getVistaNotaCursos()){
            tmodel.addRow(new Object[]{
                ob.getNota().getPc1(),
                ob.getNota().getPc2(),
                ob.getNota().getPc3(),
                ob.getNota().getExa1(),
                ob.getNota().getExa2(),
                ob.getNota().getExa3(),
                ob.getCurso().getNombreCurso()
            });
        }
    }
    
    private void mostrarNotasCursoById(Curso curso) {
        tmodel.setRowCount(0);
        for (var o : controlNotas.getNotas()) {
            tmodel.addRow(new Object[]{
                o.getPc1(), o.getPc2(), o.getPc3(), o.getExa1(), o.getExa2(), o.getExa3(), curso.getNombreCurso()
            });
        }
    }
    private void mostrarProfesor(Curso curso){
        txtProfesorAcargo.setText(curso.getNombreProfesor());
    }
    
    private void mostrarPromedioCurso(){
        double promedioFinal= Double.parseDouble(txtPromedioPractica.getText())*0.4+
                Double.parseDouble(txtPromedioExamen.getText())*0.6;
        txtPromedioNotas.setText(String.valueOf(promedioFinal));
    }
    
    private void mostrarPromedioPractica(){
        double promPractica=controlNotas.calcularPromedioPractica();
        txtPromedioPractica.setText(String.valueOf(promPractica));
    }
    
    private void mostrarPromedioExamen(){
        double promExamen=controlNotas.calcularPromedioExamen();
        txtPromedioExamen.setText(String.valueOf(promExamen));
    }

    private void cargarListaCursos() {
        for (var curso : controlNotas.getCursosAlumno()) {
            cbCursos.addItem(curso.getNombreCurso());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMostrar;
    private javax.swing.JComboBox<String> cbCursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbCursos;
    private javax.swing.JTextField txtProfesorAcargo;
    private javax.swing.JTextField txtPromedioExamen;
    private javax.swing.JTextField txtPromedioNotas;
    private javax.swing.JTextField txtPromedioPractica;
    // End of variables declaration//GEN-END:variables
}
