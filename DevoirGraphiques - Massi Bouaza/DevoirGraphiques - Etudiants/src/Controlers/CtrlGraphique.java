package Controlers;

import Entities.DatasGraph;
import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlGraphique
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlGraphique() {
        cnx = ConnexionBDD.getCnx();
    }


    public HashMap<Double, Double>GetDataGraphique1(){
        HashMap<Double,Double> datas = new HashMap();

        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT round(AVG(employe.salaireEmp),0),employe.ageEmp FROM employe GROUP BY employe.ageEmp order by ageEmp");
            rs= ps.executeQuery();
            while (rs.next()){
                datas.put(rs.getDouble(2), rs.getDouble(1) );
            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, e);
        }return datas;


    }
    public HashMap<Integer,String[]> GetDatasGraphique4(){
        HashMap<Integer,String[]> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("select vente.nomMagasin, vente.montant,vente.nomSemestre\n" +
                    "from vente;");
            rs = ps.executeQuery();
            int i = 1;
            while(rs.next())
            {
                datas.put(i, new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;

    }
    public HashMap<String,Integer> GetDatasGraphique2()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT sexe,COUNT(sexe)/2,COUNT(sexe)-59 FROM employe WHERE sexe=\"homme\";");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(CtrlGraphique.class.getName()).log(Level.SEVERE, null, e);
        }
        return datas;
    }


}
