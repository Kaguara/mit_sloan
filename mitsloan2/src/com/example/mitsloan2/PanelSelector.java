package com.example.mitsloan2;

import com.example.mitsloan2.panels.EnergyPanel;
import com.example.mitsloan2.panels.HealthCarePanel;
import com.example.mitsloan2.panels.IncubationPanel;
import com.example.mitsloan2.panels.InvestmentPanel;
import com.example.mitsloan2.panels.KeyNotePanel;
import com.example.mitsloan2.panels.TechnologyPanel;
import com.example.mitsloan2.panels.WomenPanel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PanelSelector extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panel_selection);
	}
	
	public void openKeyNote(View v){
		Intent keynote_intent = new Intent(this, KeyNotePanel.class);
    	startActivity(keynote_intent);
	}
	
	public void openEnergyPanel(View v){
		Intent energy_intent = new Intent(this, EnergyPanel.class);
    	startActivity(energy_intent);
	}
	
	public void openHealthPanel(View v){
		Intent health_intent = new Intent(this, HealthCarePanel.class);
    	startActivity(health_intent);
	}
	
	public void openInvestmentPanel(View v){
		Intent investment_intent = new Intent(this, InvestmentPanel.class);
    	startActivity(investment_intent);
	}
	
	public void openTechnologyPanel(View v){
		Intent tech_intent = new Intent(this, TechnologyPanel.class);
    	startActivity(tech_intent);
	}
	
	public void openIncubationPanel(View v){
		Intent incubation_intent = new Intent(this, IncubationPanel.class);
    	startActivity(incubation_intent);
	}
	
	public void openWomenPanel(View v){
		Intent women_intent = new Intent(this, WomenPanel.class);
    	startActivity(women_intent);
	}
	

}
