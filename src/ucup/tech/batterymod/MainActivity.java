package ucup.tech.batterymod;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.provider.Settings.System;
import android.widget.Toast;

public class MainActivity extends PreferenceActivity {
	ListPreference mListP;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.main);

		mListP = (ListPreference)findPreference("prefPos");
		mListP.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				changePos(newValue.toString());
				changeLp();
				return false;
			}
		});
		changeLp();
	}
	private void changePos(String s){
		System.putString(getContentResolver(), "ucup_batt_pos", s);
		Intent i = new Intent("ucup.tech.battery");
		this.sendBroadcast(i);
	}
	private void changeLp(){
		String s = System.getString(getContentResolver(), "ucup_batt_pos");
		if(s == null){
			s = "left";
		}
		if(s.equals("left"))
			mListP.setValueIndex(0);
		else
			mListP.setValueIndex(1);
	}
}
