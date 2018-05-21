package com.gnuey.one.component;

import com.gnuey.one.ui.onepager.OneTabPage;
import com.gnuey.one.ui.onepager.article.OneArticleView;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    OneTabPage inject(OneTabPage oneTabPage);
    OneArticleView inject(OneArticleView oneArticleView);
}
