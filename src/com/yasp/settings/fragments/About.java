/*
 * Copyright (C) 2020 Yet Another AOSP Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yasp.settings.fragments;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.SearchIndexable;

@SearchIndexable
public class About extends SettingsPreferenceFragment {

    private static final String PREF_MAINTAINER = "maintainer";
    private static final String PREF_KERNEL = "kernel";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.yaap_settings_about);
        final Resources res = getResources();

        Preference maintainerName = findPreference(PREF_MAINTAINER);
        if (maintainerName.getTitle().equals("")) {
            maintainerName.setVisible(false);
        } else if (res.getString(R.string.maintainer_telegram).equals("")) {
            maintainerName.setEnabled(false);
        }

        Preference kernelName = findPreference(PREF_KERNEL);
        if (kernelName.getTitle().equals("")) {
            kernelName.setVisible(false);
        } else if (res.getString(R.string.kernel_telegram).equals("")) {
            kernelName.setEnabled(false);
        }
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.YASP;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.yaap_settings_about);
}
