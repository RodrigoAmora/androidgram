package br.com.rodrigoamora.androidgram.component;

import br.com.rodrigoamora.androidgram.module.ListPhotosInstagramModule;
import br.com.rodrigoamora.androidgram.ui.fragment.ListPhotosInstagramFragment;
import dagger.Component;

@Component(modules = ListPhotosInstagramModule.class)
public interface ListPhotosInstagramComponent {

    void inject(ListPhotosInstagramFragment fragment);

}
