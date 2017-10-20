package me.toptas.rssreader.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.toptas.rssreader.di.component.DaggerFragmentComponent;
import me.toptas.rssreader.di.component.FragmentComponent;
import me.toptas.rssreader.di.module.FragmentModule;
import me.toptas.rssreader.model.RError;

/**
 * Created by faruktoptas on 29/01/17.
 */

public abstract class BaseFragment<T extends BaseMvpPresenter> extends Fragment implements BaseView {

    @Inject
    T mPresenter;

    FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .build();
        injectDependencies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentResource(), container, false);
        ButterKnife.bind(this, view);
        mPresenter.attach(this);
        init(savedInstanceState);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.detach();
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    /**
     * Getter for the presenter
     *
     * @return the present for the activity
     */
    protected T getPresenter() {
        return mPresenter;
    }

    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @LayoutRes
    protected abstract int getContentResource();

    /**
     * Initializations
     */
    protected abstract void init(@Nullable Bundle state);

    /**
     * Injecting dependencies
     */
    protected abstract void injectDependencies();

    @Override
    public void onFail(RError error) {
        if (isAdded()) {
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
