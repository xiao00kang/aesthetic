package com.afollestad.aestheticsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.afollestad.aesthetic.Aesthetic;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/** @author Aidan Follestad (afollestad) */
public class MainFragment extends Fragment {

  @BindView(R.id.switch_theme)
  SwitchCompat switchThemeView;

  @BindView(R.id.spinner)
  Spinner spinnerView;

  private Unbinder unbinder;

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);

    // Update the dark theme switch to the last saved isDark value.
    Aesthetic.get().isDark().take(1).subscribe(isDark -> switchThemeView.setChecked(isDark));

    // Further view setup
    ArrayAdapter<String> spinnerAdapter =
        new ArrayAdapter<>(
            getContext(),
            R.layout.list_item_spinner,
            new String[] {"One", "Two", "Three", "Four", "Five", "Six"});
    spinnerAdapter.setDropDownViewResource(R.layout.list_item_spinner_dropdown);
    spinnerView.setAdapter(spinnerAdapter);
  }

  @Override
  public void onDestroyView() {
    unbinder.unbind();
    super.onDestroyView();
  }

  @OnClick(R.id.switch_theme)
  public void onThemeChange(SwitchCompat switchCompat) {
    if (switchCompat.isChecked()) {
      Aesthetic.get().activityTheme(R.style.AppThemeDark).isDark(true).apply();
    } else {
      Aesthetic.get().activityTheme(R.style.AppTheme).isDark(false).apply();
    }
  }

  @OnClick({R.id.btn_red, R.id.btn_purple, R.id.btn_blue, R.id.btn_green, R.id.btn_white})
  public void onClickButton(View view) {
    switch (view.getId()) {
      case R.id.btn_red:
        Aesthetic.get()
            .primaryColorRes(R.color.md_red)
            .accentColorRes(R.color.md_blue_grey)
            .statusBarColorAuto()
            .navBarColorAuto()
            .apply();
        break;
      case R.id.btn_purple:
        Aesthetic.get()
            .primaryColorRes(R.color.md_purple)
            .accentColorRes(R.color.md_lime)
            .statusBarColorAuto()
            .navBarColorAuto()
            .apply();
        break;
      case R.id.btn_blue:
        Aesthetic.get()
            .primaryColorRes(R.color.md_blue)
            .accentColorRes(R.color.md_pink)
            .statusBarColorAuto()
            .navBarColorAuto()
            .apply();
        break;
      case R.id.btn_green:
        Aesthetic.get()
            .primaryColorRes(R.color.md_green)
            .accentColorRes(R.color.md_blue_grey)
            .statusBarColorAuto()
            .navBarColorAuto()
            .apply();
        break;
      case R.id.btn_white:
        Aesthetic.get()
            .primaryColorRes(R.color.md_white)
            .accentColorRes(R.color.md_blue)
            .statusBarColorAuto()
            .navBarColorAuto()
            .apply();
        break;
    }
  }
}
