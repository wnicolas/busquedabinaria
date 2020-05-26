package javaapplication94;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;

public class BusquedaBinaria {

    public static int contador = 0;

    public static int busquedaBinaria(int a[], int mitad, int x, int llave, int i, int j) {

        if (i > j) {
            contador++;
            System.out.println("El número no existe");
        } else if (llave > x) {

            i = (mitad + 1);
            mitad = (i + j) / 2;
            x = a[mitad];
            busquedaBinaria(a, mitad, x, llave, i, j);
            contador += 10;

        } else if (llave < x) {

            j = (mitad - 1);
            mitad = (i + j) / 2;
            x = a[mitad];
            busquedaBinaria(a, mitad, x, llave, i, j);
            contador += 11;

        } else {
            contador += 4;
            System.out.println("");
            System.out.println("El número existe");
        }
        return contador;
    }

    public static int[] generaMatriz(int n) {
        int a[] = new int[n];
        a[0]=1;
        for (int i = 1; i <n; i++) {
            a[i] =(int) (a[i-1] + Math.random()*10+1);
            System.out.print(" " + a[i]);

        }
        return a;
    }

    public static void main(String[] args) {

        XYSeries puntos = new XYSeries("Búsqueda");
        for (int i = 0; i < 2000; i+=5) {
            contador=0;
            int a[] = generaMatriz(i+1);
            int mitad, x;
            int llave = a[(int)(Math.random()*i)];
            mitad = (0 + (a.length - 1)) / 2;
            x = a[mitad];
            System.out.println(contador);
            puntos.add(i,busquedaBinaria(a, mitad, x, llave, 0, (a.length - 1)));
        }

        XYSeriesCollection dataSet = new XYSeriesCollection();
        dataSet.addSeries(puntos);

        JFreeChart xyLineChart = ChartFactory.createXYLineChart("Búsqueda binaria", "Intentos", "Operaciones elementales", dataSet, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = xyLineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        plot.setRenderer(renderer);
        
        ChartPanel panel=new ChartPanel(xyLineChart);
        
        JFrame ventana=new JFrame();
        ventana.setVisible(true);
        ventana.setSize(1500,600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ventana.add(panel);
        
        
    }

}
