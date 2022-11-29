package Vues;

import Controlers.CtrlGraphique;
import Entities.DatasGraph;
import Tools.ConnexionBDD;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Map;

public class FrmGraphique extends JFrame{
    private JPanel pnlGraph1;
    private JPanel pnlGraph2;
    private JPanel pnlGraph3;
    private JPanel pnlGraph4;
    private JPanel pnlRoot;
    CtrlGraphique ctrlGraphique;

    public FrmGraphique() throws SQLException, ClassNotFoundException {
        this.setTitle("Devoir graphique");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ConnexionBDD cnx = new ConnexionBDD();
        ctrlGraphique = new CtrlGraphique();

        DefaultPieDataset donnees2 = new DefaultPieDataset();

        for (Map.Entry valeur : ctrlGraphique.GetDatasGraphique2().entrySet())
        {
            donnees2.setValue(valeur.getKey().toString(), Integer.parseInt(valeur.getValue().toString()));
        }

        JFreeChart chart2 = ChartFactory.createPieChart(
                "Nombre d'homme et de femme",
                donnees2,
                true,
                true,
                true
        );
       // RingPlot plot = (RingPlot) chart2.getPlot();
       // plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}"));
        //.setSectionDepth(0.5);
        ChartFrame frame = new ChartFrame("Graphique n°2", chart2);
        frame.pack();
        frame.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                ctrlGraphique = new CtrlGraphique();
                  DefaultCategoryDataset donnees4 = new DefaultCategoryDataset();

                 for (Map.Entry valeur : ctrlGraphique.GetDatasGraphique4().entrySet())
                  {
                     Double montant = Double.parseDouble(((String[])valeur.getValue())[1].toString());
                     String nomMagsin = ((String[])valeur.getValue())[0].toString();
                     String nomSemestre = ((String[])valeur.getValue())[2].toString();

                 donnees4.setValue(montant,nomMagsin,nomSemestre);
                 }

                  JFreeChart chart4 = ChartFactory.createBarChart(
                  "Montant des ventees par Magasin",
                   "Magasins",
                   "Montant des vente",
                   donnees4,
                    PlotOrientation.VERTICAL,
                    true, true, false);
                    ChartFrame frame = new ChartFrame("Graphique n°4", chart4);
                    frame.pack();
                   frame.setVisible(true);


                DefaultCategoryDataset donnees = new DefaultCategoryDataset();

                for(Map.Entry valeur : ctrlGraphique.GetDataGraphique1().entrySet()){
                    donnees.setValue(Double.parseDouble(valeur.getValue().toString()),"Moyennes" ,valeur.getKey().toString());
                }
                JFreeChart chart1 = ChartFactory.createLineChart("Moyenne des ages", "age", "salaire", donnees);
                ChartFrame fra = new ChartFrame("Graphique n°1", chart1);
                fra.pack();
                fra.setVisible(true);

            }
        });
    }
}
