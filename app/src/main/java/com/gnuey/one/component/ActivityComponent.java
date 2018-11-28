package com.gnuey.one.component;

import com.gnuey.one.scope.ActivityScope;
import com.gnuey.one.ui.activity.read.ReadActivity;

import dagger.Component;

/**
 * Created by gnuey on 2018/11/26/026
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    ReadActivity inject(ReadActivity readActivity);
}
