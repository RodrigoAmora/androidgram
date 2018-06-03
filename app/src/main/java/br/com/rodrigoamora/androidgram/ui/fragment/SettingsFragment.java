package br.com.rodrigoamora.androidgram.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import br.com.rodrigoamora.androidgram.R;
import br.com.rodrigoamora.androidgram.dao.TokensDao;
import br.com.rodrigoamora.androidgram.ui.activity.LoginInstagramActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingsFragment extends Fragment {

    @BindView(R.id.integrate_instagram)
    Switch integrateInstagram;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        if (!TokensDao.getAccessTokenInstagram(getActivity()).equals("")) {
            integrateInstagram.setChecked(true);
        }

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.integrate_instagram)
    public void loginInstagram() {
        if (integrateInstagram.isChecked()) {
            Intent intent = new Intent(getActivity(), LoginInstagramActivity.class);
            startActivity(intent);
        } else {
            TokensDao.deleteAccessTokenInstagram(getActivity());
        }
    }

}
