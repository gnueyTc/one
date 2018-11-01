package com.gnuey.one.component;

import com.gnuey.one.scope.FragmentScope;
import com.gnuey.one.ui.onepager.OneTabLayout;
import com.gnuey.one.ui.onepager.article.OneArticleView;



import dagger.Component;
@FragmentScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    OneArticleView inject(OneArticleView oneArticleView);
}
