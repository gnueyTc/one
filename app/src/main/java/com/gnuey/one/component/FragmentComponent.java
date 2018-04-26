package com.gnuey.one.component;

import com.gnuey.one.ui.onepager.OneTabPage;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    OneTabPage inject(OneTabPage oneTabPage);
}
