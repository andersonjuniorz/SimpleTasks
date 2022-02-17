[33mcommit 646bad8b429878d8adb76a9aaa93866086808901[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m, [m[1;31morigin/master[m[33m)[m
Author: Anderson Júnior <dev.andersonjunior@gmail.com>
Date:   Fri Feb 11 18:01:30 2022 -0300

    Updated login. Now after to do login, the user is redirected to the interface

[1mdiff --git a/nbproject/private/private.xml b/nbproject/private/private.xml[m
[1mindex a25cc06..e328b78 100644[m
[1m--- a/nbproject/private/private.xml[m
[1m+++ b/nbproject/private/private.xml[m
[36m@@ -4,8 +4,8 @@[m
     <open-files xmlns="http://www.netbeans.org/ns/projectui-open-files/2">[m
         <group>[m
             <file>file:/C:/Users/junio/OneDrive/Documentos/NetBeansProjects/ToDoList/src/Model/TaskDAO.java</file>[m
[31m-            <file>file:/C:/Users/junio/OneDrive/Documentos/NetBeansProjects/ToDoList/src/View/Interface.java</file>[m
[31m-            <file>file:/C:/Users/junio/OneDrive/Documentos/NetBeansProjects/ToDoList/src/Model/Task.java</file>[m
[32m+[m[32m            <file>file:/C:/Users/junio/OneDrive/Documentos/NetBeansProjects/ToDoList/src/Model/UserDAO.java</file>[m
[32m+[m[32m            <file>file:/C:/Users/junio/OneDrive/Documentos/NetBeansProjects/ToDoList/src/View/LogIn.java</file>[m
         </group>[m
     </open-files>[m
 </project-private>[m
[1mdiff --git a/nbproject/project.properties b/nbproject/project.properties[m
[1mindex c5a6564..0ad919f 100644[m
[1m--- a/nbproject/project.properties[m
[1m+++ b/nbproject/project.properties[m
[36m@@ -75,7 +75,7 @@[m [mjlink.additionalmodules=[m
 jlink.additionalparam=[m
 jlink.launcher=true[m
 jlink.launcher.name=ToDoList[m
[31m-main.class=View.Interface[m
[32m+[m[32mmain.class=View.LogIn[m
 manifest.file=manifest.mf[m
 meta.inf.dir=${src.dir}/META-INF[m
 mkdist.disabled=false[m
[1mdiff --git a/src/Model/TaskDAO.java b/src/Model/TaskDAO.java[m
[1mindex e35b725..1ea189a 100644[m
[1m--- a/src/Model/TaskDAO.java[m
[1m+++ b/src/Model/TaskDAO.java[m
[36m@@ -64,7 +64,6 @@[m [mpublic class TaskDAO {[m
             while (rs.next()) {[m
                 int id = rs.getInt(1);[m
                 String tsk = rs.getString(2);[m
[31m-                //String a = null;[m
                 task.add(new Task(id, tsk));[m
             }[m
 [m
[36m@@ -86,7 +85,7 @@[m [mpublic class TaskDAO {[m
         try {[m
             Connection conn = Connect();[m
             PreparedStatement pst = conn.prepareStatement(delete);[m
[31m-            pst.setInt(2, task.getId());[m
[32m+[m[32m            pst.setInt(1, task.getId());[m
             pst.executeUpdate();[m
 [m
             conn.close();[m
[1mdiff --git a/src/Model/UserDAO.java b/src/Model/UserDAO.java[m
[1mindex 5516d8c..6ae5a60 100644[m
[1m--- a/src/Model/UserDAO.java[m
[1m+++ b/src/Model/UserDAO.java[m
[36m@@ -83,30 +83,33 @@[m [mpublic class UserDAO {[m
 [m
     }[m
 [m
[31m-    public String[] ReadLoginPass(String user) {[m
[31m-        String read = "select user_name,user_password from users where user_name = ?;";[m
[31m-        String[] userD = new String[1];[m
[32m+[m[32m    //Usado no login[m
[32m+[m[32m    public void selectUser(User user) {[m
[32m+[m[32m        String read = "select user_name, user_password from users where user_name=?";[m
[32m+[m
         try {[m
             Connection connection = Connect();[m
             PreparedStatement pst = connection.prepareStatement(read);[m
[31m-            pst.setString(1, user);[m
[32m+[m[32m            pst.setString(1, user.getUser());[m
             ResultSet rs = pst.executeQuery();[m
 [m
[32m+[m[32m            /*[m
[32m+[m[32m                Quando o usuario informado nao existe no banco, os dados anteriores[m[41m [m
[32m+[m[32m                sao considerados como corretos causando erro na verificacao do[m[41m [m
[32m+[m[32m                usuario existente, por isso resetei os dados passando nulo[m
[32m+[m[32m             */[m
[32m+[m[32m            user.setUser(null, null);[m
[32m+[m
             while (rs.next()) {[m
[31m-                userD[0] = rs.getString(1);[m
[31m-                userD[1] = rs.getString(2);[m
[32m+[m[32m                user.setUser(rs.getString(1), rs.getString(2));[m
             }[m
 [m
             connection.close();[m
             pst.close();[m
             rs.close();[m
 [m
[31m-        } catch (SQLException e) {[m
[31m-            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);[m
[31m-            return null;[m
[32m+[m[32m        } catch (Exception e) {[m
[32m+[m[32m            System.out.print("Erro: " + e);[m
         }[m
[31m-        return userD;[m
     }[m
[31m-    /* update */[m
[31m- /* delete */[m
 }[m
[1mdiff --git a/src/Model/userDAO.java b/src/Model/userDAO.java[m
[1mindex 5516d8c..6ae5a60 100644[m
[1m--- a/src/Model/userDAO.java[m
[1m+++ b/src/Model/userDAO.java[m
[36m@@ -83,30 +83,33 @@[m [mpublic class UserDAO {[m
 [m
     }[m
 [m
[31m-    public String[] ReadLoginPass(String user) {[m
[31m-        String read = "select user_name,user_password from users where user_name = ?;";[m
[31m-        String[] userD = new String[1];[m
[32m+[m[32m    //Usado no login[m
[32m+[m[32m    public void selectUser(User user) {[m
[32m+[m[32m        String read = "select user_name, user_password from users where user_name=?";[m
[32m+[m
         try {[m
             Connection connection = Connect();[m
             PreparedStatement pst = connection.prepareStatement(read);[m
[31m-            pst.setString(1, user);[m
[32m+[m[32m            pst.setString(1, user.getUser());[m
             ResultSet rs = pst.executeQuery();[m
 [m
[32m+[m[32m            /*[m
[32m+[m[32m                Quando o usuario informado nao existe no banco, os dados anteriores[m[41m [m
[32m+[m[32m                sao considerados como corretos causando erro na verificacao do[m[41m [m
[32m+[m[32m                usuario existente, por isso resetei os dados passando nulo[m
[32m+[m[32m             */[m
[32m+[m[32m            user.setUser(null, null);[m
[32m+[m
             while (rs.next()) {[m
[31m-                userD[0] = rs.getString(1);[m
[31m-                userD[1] = rs.getString(2);[m
[32m+[m[32m                user.setUser(rs.getString(1), rs.getString(2));[m
             }[m
 [m
             connection.close();[m
             pst.close();[m
             rs.close();[m
 [m
[31m-        } catch (SQLException e) {[m
[31m-            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);[m
[31m-            return null;[m
[32m+[m[32m        } catch (Exception e) {[m
[32m+[m[32m            System.out.print("Erro: " + e);[m
         }[m
[31m-        return userD;[m
     }[m
[31m-    /* update */[m
[31m- /* delete */[m
 }[m
[1mdiff --git a/src/View/Interface.java b/src/View/Interface.java[m
[1mindex b0a786a..9a70ece 100644[m
[1m--- a/src/View/Interface.java[m
[1m+++ b/src/View/Interface.java[m
[36m@@ -311,4 +311,6 @@[m [mpublic class Interface extends javax.swing.JFrame {[m
             model.addRow(new Object[]{task1.getId(),task1.getTask()});[m
         }[m
     }[m
[32m+[m[41m    [m
[32m+[m[41m    [m
 }[m
[1mdiff --git a/src/View/LogIn.form b/src/View/LogIn.form[m
[1mindex eb76ab2..3366d25 100644[m
[1m--- a/src/View/LogIn.form[m
[1m+++ b/src/View/LogIn.form[m
[36m@@ -3,10 +3,11 @@[m
 <Form version="1.3" maxVersion="1.9" type="org.netbeans.modules.form.forminfo.JDialogFormInfo">[m
   <Properties>[m
     <Property name="defaultCloseOperation" type="int" value="2"/>[m
[32m+[m[32m    <Property name="title" type="java.lang.String" value="Login"/>[m
   </Properties>[m
   <SyntheticProperties>[m
     <SyntheticProperty name="formSizePolicy" type="int" value="1"/>[m
[31m-    <SyntheticProperty name="generateCenter" type="boolean" value="false"/>[m
[32m+[m[32m    <SyntheticProperty name="generateCenter" type="boolean" value="true"/>[m
   </SyntheticProperties>[m
   <AuxValues>[m
     <AuxValue name="FormSettings_autoResourcing" type="java.lang.Integer" value="0"/>[m
[36m@@ -23,7 +24,10 @@[m
   <Layout>[m
     <DimensionLayout dim="0">[m
       <Group type="103" groupAlignment="0" attributes="0">[m
[31m-          <Component id="jPanel1" alignment="0" max="32767" attributes="0"/>[m
[32m+[m[32m          <Group type="102" alignment="0" attributes="0">[m
[32m+[m[32m              <Component id="jPanel1" min="-2" max="-2" attributes="0"/>[m
[32m+[m[32m              <EmptySpace min="0" pref="0" max="32767" attributes="0"/>[m
[32m+[m[32m          </Group>[m
       </Group>[m
     </DimensionLayout>[m
     <DimensionLayout dim="1">[m
[36m@@ -41,12 +45,11 @@[m
       <Layout>[m
         <DimensionLayout dim="0">[m
           <Group type="103" groupAlignment="0" attributes="0">[m
[31m-              <Group type="102" alignment="0" attributes="0">[m
[32m+[m[32m              <Group type="102" alignment="1" attributes="0">[m
[32m+[m[32m                  <EmptySpace max="32767" attributes="0"/>[m
[32m+[m[32m                  <Component id="jPanel2" min="-2" max="-2" attributes="0"/>[m
                   <EmptySpace max="-2" attributes="0"/>[m
[31m-                  <Group type="103" groupAlignment="0" attributes="0">[m
[31m-                      <Component id="jPanel2" max="32767" attributes="0"/>[m
[31m-                      <Component id="jPanel3" max="32767" attributes="0"/>[m
[31m-                  </Group>[m
[32m+[m[32m                  <Component id="jPanel3" min="-2" max="-2" attributes="0"/>[m
                   <EmptySpace max="-2" attributes="0"/>[m
               </Group>[m
           </Group>[m
[36m@@ -54,10 +57,11 @@[m
         <DimensionLayout dim="1">[m
           <Group type="103" groupAlignment="0" attributes="0">[m
               <Group type="102" alignment="0" attributes="0">[m
[31m-                  <EmptySpace max="-2" attributes="0"/>[m
[31m-                  <Component id="jPanel2" min="-2" max="-2" attributes="0"/>[m
[31m-                  <EmptySpace max="-2" attributes="0"/>[m
[31m-                  <Component id="jPanel3" min="-2" max="-2" attributes="0"/>[m
[32m+[m[32m                  <EmptySpace min="-2" pref="10" max="-2" attributes="0"/>[m
[32m+[m[32m                  <Group type="103" groupAlignment="0" max="-2" attributes="0">[m
[32m+[m[32m                      <Component id="jPanel3" max="32767" attributes="0"/>[m
[32m+[m[32m                      <Component id="jPanel2" max="32767" attributes="0"/>[m
[32m+[m[32m                  </Group>[m
                   <EmptySpace max="32767" attributes="0"/>[m
               </Group>[m
           </Group>[m
[36m@@ -69,17 +73,17 @@[m
           <Layout>[m
             <DimensionLayout dim="0">[m
               <Group type="103" groupAlignment="0" attributes="0">[m
[31m-                  <Group type="102" alignment="1" attributes="0">[m
[31m-                      <EmptySpace max="32767" attributes="0"/>[m
[32m+[m[32m                  <Group type="102" alignment="0" attributes="0">[m
[32m+[m[32m                      <EmptySpace min="-2" pref="72" max="-2" attributes="0"/>[m
                       <Component id="jLabel1" min="-2" max="-2" attributes="0"/>[m
[31m-                      <EmptySpace min="-2" pref="229" max="-2" attributes="0"/>[m
[32m+[m[32m                      <EmptySpace pref="77" max="32767" attributes="0"/>[m
                   </Group>[m
               </Group>[m
             </DimensionLayout>[m
             <DimensionLayout dim="1">[m
               <Group type="103" groupAlignment="0" attributes="0">[m
                   <Group type="102" alignment="0" attributes="0">[m
[31m-                      <EmptySpace max="-2" attributes="0"/>[m
[32m+[m[32m                      <EmptySpace min="-2" pref="24" max="-2" attributes="0"/>[m
                       <Component id="jLabel1" min="-2" max="-2" attributes="0"/>[m
                       <EmptySpace max="32767" attributes="0"/>[m
                   </Group>[m
[36m@@ -109,28 +113,24 @@[m
           <Layout>[m
             <DimensionLayout dim="0">[m
               <Group type="103" groupAlignment="0" attributes="0">[m
[31m-                  <Group type="102" attributes="0">[m
[32m+[m[32m                  <Group type="102" alignment="0" attributes="0">[m
[32m+[m[32m                      <EmptySpace min="-2" pref="86" max="-2" attributes="0"/>[m
                       <Group type="103" groupAlignment="0" attributes="0">[m
[31m-                          <Group type="102" attributes="0">[m
[31m-                              <EmptySpace min="-2" pref="137" max="-2" attributes="0"/>[m
[31m-                              <Group type="103" groupAlignment="0" attributes="0">[m
[31m-                                  <Component id="ckb_remmember" min="-2" max="-2" attributes="0"/>[m
[31m-                                  <Group type="103" groupAlignment="0" max="-2" attributes="0">[m
[31m-                                      <Component id="jLabel2" alignment="0" min="-2" max="-2" attributes="0"/>[m
[31m-                                      <Component id="jLabel3" alignment="0" min="-2" max="-2" attributes="0"/>[m
[31m-                                      <Component id="txt_user" max="32767" attributes="0"/>[m
[31m-                                      <Component id="txt_pass" alignment="0" max="32767" attributes="0"/>[m
[31m-                                      <Component id="btn_cadastrar" alignment="1" pref="263" max="32767" attributes="0"/>[m
[31m-                                      <Component id="btn_login" alignment="1" pref="263" max="32767" attributes="0"/>[m
[31m-                                  </Group>[m
[31m-                              </Group>[m
[32m+[m[32m                          <Component id="ckb_remmember" min="-2" max="-2" attributes="0"/>[m
[32m+[m[32m                          <Group type="103" alignment="0" groupAlignment="0" max="-2" attributes="0">[m
[32m+[m[32m                              <Component id="jLabel2" alignment="0" min="-2" max="-2" attributes="0"/>[m
[32m+[m[32m                              <Component id="jLabel3" alignment="0" min="-2" max="-2" attributes="0"/>[m
[32m+[m[32m                              <Component id="txt_user" max="32767" attributes="0"/>[m
[32m+[m[32m                              <Component id="txt_pass" alignment="0" max="32767" attributes="0"/>[m
[32m+[m[32m                              <Component id="btn_cadastrar" alignment="1" max="32767" attributes="0"/>[m
[32m+[m[32m                              <Component id="btn_login" alignment="1" min="-2" pref="263" max="-2" attributes="0"/>[m
                           </Group>[m
                           <Group type="102" alignment="0" attributes="0">[m
[31m-                              <EmptySpace min="-2" pref="210" max="-2" attributes="0"/>[m
[32m+[m[32m                              <EmptySpace min="-2" pref="73" max="-2" attributes="0"/>[m
                               <Component id="btn_forgotPass" min="-2" max="-2" attributes="0"/>[m
                           </Group>[m
                       </Group>[m
[31m-                      <EmptySpace pref="142" max="32767" attributes="0"/>[m
[32m+[m[32m                      <EmptySpace pref="89" max="32767" attributes="0"/>[m
                   </Group>[m
               </Group>[m
             </DimensionLayout>[m
[36m@@ -176,8 +176,14 @@[m
               </Properties>[m
             </Component>[m
             <Component class="javax.swing.JTextField" name="txt_user">[m
[32m+[m[32m              <Events>[m
[32m+[m[32m                <EventHandler event="keyPressed" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txt_userKeyPressed"/>[m
[32m+[m[32m              </Events>[m
             </Component>[m
             <Component class="javax.swing.JPasswordField" name="txt_pass">[m
[32m+[m[32m              <Events>[m
[32m+[m[32m                <EventHandler event="keyPressed" listener="java.awt.event.KeyListener" parameters="java.awt.event.KeyEvent" handler="txt_passKeyPressed"/>[m
[32m+[m[32m              </Events>[m
             </Component>[m
             <Component class="javax.swing.JCheckBox" name="ckb_remmember">[m
               <Properties>[m
[1mdiff --git a/src/View/LogIn.java b/src/View/LogIn.java[m
[1mindex 6fb0a23..50c61a7 100644[m
[1m--- a/src/View/LogIn.java[m
[1m+++ b/src/View/LogIn.java[m
[36m@@ -3,6 +3,7 @@[m [mpackage View;[m
 import java.awt.Cursor;[m
 import Model.User;[m
 import Model.UserDAO;[m
[32m+[m[32mimport java.awt.event.KeyEvent;[m
 import java.util.ArrayList;[m
 import javax.swing.JOptionPane;[m
 [m
[36m@@ -39,6 +40,7 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         btn_login = new javax.swing.JButton();[m
 [m
         setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);[m
[32m+[m[32m        setTitle("Login");[m
 [m
         jLabel1.setFont(new java.awt.Font("David", 0, 36)); // NOI18N[m
         jLabel1.setText("Login");[m
[36m@@ -47,15 +49,15 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         jPanel2.setLayout(jPanel2Layout);[m
         jPanel2Layout.setHorizontalGroup([m
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[31m-            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()[m
[31m-                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)[m
[32m+[m[32m            .addGroup(jPanel2Layout.createSequentialGroup()[m
[32m+[m[32m                .addGap(72, 72, 72)[m
                 .addComponent(jLabel1)[m
[31m-                .addGap(229, 229, 229))[m
[32m+[m[32m                .addContainerGap(77, Short.MAX_VALUE))[m
         );[m
         jPanel2Layout.setVerticalGroup([m
             jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
             .addGroup(jPanel2Layout.createSequentialGroup()[m
[31m-                .addContainerGap()[m
[32m+[m[32m                .addGap(24, 24, 24)[m
                 .addComponent(jLabel1)[m
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))[m
         );[m
[36m@@ -68,6 +70,18 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         jLabel3.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N[m
         jLabel3.setText("Senha:");[m
 [m
[32m+[m[32m        txt_user.addKeyListener(new java.awt.event.KeyAdapter() {[m
[32m+[m[32m            public void keyPressed(java.awt.event.KeyEvent evt) {[m
[32m+[m[32m                txt_userKeyPressed(evt);[m
[32m+[m[32m            }[m
[32m+[m[32m        });[m
[32m+[m
[32m+[m[32m        txt_pass.addKeyListener(new java.awt.event.KeyAdapter() {[m
[32m+[m[32m            public void keyPressed(java.awt.event.KeyEvent evt) {[m
[32m+[m[32m                txt_passKeyPressed(evt);[m
[32m+[m[32m            }[m
[32m+[m[32m        });[m
[32m+[m
         ckb_remmember.setText("Lembrar senha");[m
 [m
         btn_forgotPass.setFont(new java.awt.Font("Noto Serif", 1, 12)); // NOI18N[m
[36m@@ -106,22 +120,20 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         jPanel3Layout.setHorizontalGroup([m
             jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
             .addGroup(jPanel3Layout.createSequentialGroup()[m
[32m+[m[32m                .addGap(86, 86, 86)[m
                 .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[32m+[m[32m                    .addComponent(ckb_remmember)[m
[32m+[m[32m                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)[m
[32m+[m[32m                        .addComponent(jLabel2)[m
[32m+[m[32m                        .addComponent(jLabel3)[m
[32m+[m[32m                        .addComponent(txt_user)[m
[32m+[m[32m                        .addComponent(txt_pass)[m
[32m+[m[32m                        .addComponent(btn_cadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)[m
[32m+[m[32m                        .addComponent(btn_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))[m
                     .addGroup(jPanel3Layout.createSequentialGroup()[m
[31m-                        .addGap(137, 137, 137)[m
[31m-                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[31m-                            .addComponent(ckb_remmember)[m
[31m-                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)[m
[31m-                                .addComponent(jLabel2)[m
[31m-                                .addComponent(jLabel3)[m
[31m-                                .addComponent(txt_user)[m
[31m-                                .addComponent(txt_pass)[m
[31m-                                .addComponent(btn_cadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)[m
[31m-                                .addComponent(btn_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))))[m
[31m-                    .addGroup(jPanel3Layout.createSequentialGroup()[m
[31m-                        .addGap(210, 210, 210)[m
[32m+[m[32m                        .addGap(73, 73, 73)[m
                         .addComponent(btn_forgotPass)))[m
[31m-                .addContainerGap(142, Short.MAX_VALUE))[m
[32m+[m[32m                .addContainerGap(89, Short.MAX_VALUE))[m
         );[m
         jPanel3Layout.setVerticalGroup([m
             jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[36m@@ -149,20 +161,20 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         jPanel1.setLayout(jPanel1Layout);[m
         jPanel1Layout.setHorizontalGroup([m
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[31m-            .addGroup(jPanel1Layout.createSequentialGroup()[m
[31m-                .addContainerGap()[m
[31m-                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[31m-                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)[m
[31m-                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))[m
[32m+[m[32m            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()[m
[32m+[m[32m                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)[m
[32m+[m[32m                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)[m
[32m+[m[32m                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)[m
[32m+[m[32m                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)[m
                 .addContainerGap())[m
         );[m
         jPanel1Layout.setVerticalGroup([m
             jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
             .addGroup(jPanel1Layout.createSequentialGroup()[m
[31m-                .addContainerGap()[m
[31m-                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)[m
[31m-                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)[m
[31m-                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)[m
[32m+[m[32m                .addGap(10, 10, 10)[m
[32m+[m[32m                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)[m
[32m+[m[32m                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)[m
[32m+[m[32m                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))[m
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))[m
         );[m
 [m
[36m@@ -170,7 +182,9 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         getContentPane().setLayout(layout);[m
         layout.setHorizontalGroup([m
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[31m-            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)[m
[32m+[m[32m            .addGroup(layout.createSequentialGroup()[m
[32m+[m[32m                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)[m
[32m+[m[32m                .addGap(0, 0, Short.MAX_VALUE))[m
         );[m
         layout.setVerticalGroup([m
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)[m
[36m@@ -180,6 +194,7 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         );[m
 [m
         pack();[m
[32m+[m[32m        setLocationRelativeTo(null);[m
     }// </editor-fold>//GEN-END:initComponents[m
 [m
     private void btn_forgotPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_forgotPassMouseClicked[m
[36m@@ -206,11 +221,50 @@[m [mpublic class LogIn extends javax.swing.JDialog {[m
         String username = txt_user.getText().trim();[m
         String pass = String.valueOf(txt_pass.getPassword());[m
 [m
[32m+[m[32m        //Setando dados no model, passando eles no dao[m
[32m+[m[32m        //dao seta dados no model[m
[32m+[m[32m        user.setUser(username, pass);[m
[32m+[m[32m        dao.selectUser(user);[m
[32m+[m
[32m+[m[32m        //Campo vazio[m
[32m+[m[32m        if (username.equals("") || pass.equals("")) {[m
[32m+[m[32m            JOptionPane.showMessageDialog(null, "Campo vazio!");[m
 [m
[32m+[m[32m        } else {[m
[32m+[m[32m            //verifica se usuario existe[m
[32m+[m[32m            if (user.getUser() == null) {[m
[32m+[m[32m                JOptionPane.showMessageDialog(null, "Usuário não existe!");[m
 [m
[32m+[m[32m                //Verifica se os dados estao corretos[m
[32m+[m[32m            } else if (username.equals(user.getUser()) && pass.equals(user.getPassword())) {[m
[32m+[m[32m                this.dispose();[m
 [m
[32m+[m[32m                //Open interface[m
[32m+[m[32m                Interface intfc = new Interface();[m
[32m+[m[32m                intfc.setVisible(true);[m
[32m+[m[32m                JOptionPane.showMessageDialog(null, "Welcome " + user.getUser() + "!");[m
[32m+[m
[32m+[m[32m                //senao exibe mensagem de erro[m
[32m+[m[32m            } else {[m
[32m+[m[32m                JOptionPane.showMessageDialog(null, "Login ou senha inválido.");[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
     }//GEN-LAST:event_btn_loginActionPerformed[m
 [m
[32m+[m[32m    private void txt_userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_userKeyPressed[m
[32m+[m[32m        //Se clicar Enter, vai para o próximo campo[m
[32m+[m[32m        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {[m
[32m+[m[32m            txt_pass.requestFocus();[m
[32m+[m[32m        }[m
[32m+[m[32m    }//GEN-LAST:event_txt_userKeyPressed[m
[32m+[m
[32m+[m[32m    private void txt_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyPressed[m
[32m+[m[32m        //Se clicar Enter, vai para o próximo campo[m
[32m+[m[32m        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {[m
[32m+[m[32m            btn_login.doClick();[m
[32m+[m[32m        }[m
[32m+[m[32m    }//GEN-LAST:event_txt_passKeyPressed[m
[32m+[m
     /**[m
      * @param args the command line arguments[m
      */[m
