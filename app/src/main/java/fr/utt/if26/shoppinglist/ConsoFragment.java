package fr.utt.if26.shoppinglist;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.utt.if26.shoppinglist.entities.embedded.AlimentAndCompose;
import fr.utt.if26.shoppinglist.viewModels.AlimentViewModel;

public class ConsoFragment extends Fragment {

    private AlimentViewModel alimentViewModel;
    private Map<String, Integer> mapCategorie;
    private List<String> categories;
    private BarChart barChart;
    private BarData barData;
    private BarDataSet barDataSet;
    private ArrayList barEntriesArrayList;

    public ConsoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_conso, container, false);

        barChart = result.findViewById(R.id.fragment_conso_bc);

        categories = new ArrayList<String>();
        categories.add("Céréales");
        categories.add("Boissons");
        categories.add("Viande et poisson");
        categories.add("Légumes et fruits");
        categories.add("Produits sucrés");
        categories.add("Produits laitiers");
        categories.add("Plats composés");
        categories.add("Autre");

        mapCategorie = new HashMap<String, Integer>();
        mapCategorie.put(categories.get(0), 0);
        mapCategorie.put(categories.get(1), 0);
        mapCategorie.put(categories.get(2), 0);
        mapCategorie.put(categories.get(3), 0);
        mapCategorie.put(categories.get(4), 0);
        mapCategorie.put(categories.get(5), 0);
        mapCategorie.put(categories.get(6), 0);
        mapCategorie.put(categories.get(7), 0);

        createBarChart();

        alimentViewModel = new ViewModelProvider(this).get(AlimentViewModel.class);
        alimentViewModel.getAlimentAndCompose().observe(getViewLifecycleOwner(), options -> {
            for (AlimentAndCompose alimentAndCompose : options) {
                mapCategorie.put(alimentAndCompose.aliment.getCategorie(),
                        mapCategorie.get(alimentAndCompose.aliment.getCategorie()) + 1
                );
            }
            createBarChart();
        });

        return result;
    }

    private void createBarChart() {
        barEntriesArrayList = new ArrayList();
        barEntriesArrayList.add(new BarEntry(0f, mapCategorie.get(categories.get(0))));
        barEntriesArrayList.add(new BarEntry(1f, mapCategorie.get(categories.get(1))));
        barEntriesArrayList.add(new BarEntry(2f, mapCategorie.get(categories.get(2))));
        barEntriesArrayList.add(new BarEntry(3f, mapCategorie.get(categories.get(3))));
        barEntriesArrayList.add(new BarEntry(4f, mapCategorie.get(categories.get(4))));
        barEntriesArrayList.add(new BarEntry(5f, mapCategorie.get(categories.get(5))));
        barEntriesArrayList.add(new BarEntry(6f, mapCategorie.get(categories.get(6))));
        barEntriesArrayList.add(new BarEntry(7f, mapCategorie.get(categories.get(7))));
        barDataSet = new BarDataSet(barEntriesArrayList, "Consommation par catégorie");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColor(Color.BLUE);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(categories));
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(14f);
        barChart.setVisibleXRangeMaximum(3);
        barChart.setDragEnabled(true);
        barData.setBarWidth(0.9f);
        barChart.getXAxis().setAxisMinimum(-0.5f);
        barChart.getAxisLeft().setAxisMinimum(0);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.setExtraBottomOffset(10f);
        barChart.animate();
        barChart.invalidate();
    }

}