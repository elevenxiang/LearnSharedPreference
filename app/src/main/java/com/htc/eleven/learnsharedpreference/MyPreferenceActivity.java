package com.htc.eleven.learnsharedpreference;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyPreferenceActivity extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    private PreferenceCategory category_checkboxPreference;
    private PreferenceCategory category_editPreference;
    private PreferenceCategory category_listPreference;

    private CheckBoxPreference boxPreference;
    private ListPreference listPreference;
    private EditTextPreference editTextPreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.activity_my_preference);

        category_checkboxPreference = (PreferenceCategory) getPreferenceManager().findPreference("mylocation");
        category_editPreference = (PreferenceCategory) getPreferenceManager().findPreference("mymsg");
        category_listPreference = (PreferenceCategory) getPreferenceManager().findPreference("language");

        // we could also get the Preference from getPreferenceManager().
//        boxPreference = (CheckBoxPreference) getPreferenceManager().findPreference("wireless_network");
        boxPreference = (CheckBoxPreference) category_checkboxPreference.findPreference("wireless_network");
        boxPreference.setOnPreferenceChangeListener(this);

        listPreference = (ListPreference) category_listPreference.findPreference("list");
        listPreference.setOnPreferenceChangeListener(this);

        editTextPreference = (EditTextPreference) category_editPreference.findPreference("myname");
        editTextPreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        switch (preference.getKey()) {
            case "wireless_network":
                CheckBoxPreference cb = (CheckBoxPreference) preference;
                boolean result = !cb.isChecked();
                Toast.makeText(getContext(), cb.getSummary().toString()+ ": " + result, Toast.LENGTH_SHORT).show();
                break;

            case "list":
                ListPreference list = (ListPreference) preference;
                list.setSummary(list.getEntry() + " : " + list.getValue());
                Toast.makeText(getContext(), list.getEntry() + " : " + list.getValue(), Toast.LENGTH_SHORT).show();
                break;

            case "myname":
                EditTextPreference edit = (EditTextPreference) preference;
                Toast.makeText(getContext(), edit.getEditText().getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    // can't use onCreateView for PreferenceFragment !!!
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.activity_my_preference, container);
//        return root;
//    }


}
