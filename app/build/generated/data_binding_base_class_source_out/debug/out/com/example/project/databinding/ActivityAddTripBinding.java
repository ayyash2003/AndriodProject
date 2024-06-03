// Generated by view binder compiler. Do not edit!
package com.example.project.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.project.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddTripBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final EditText descriptionFld;

  @NonNull
  public final TextView descriptionlbl;

  @NonNull
  public final EditText destinationFld;

  @NonNull
  public final TextView destinationlbl;

  @NonNull
  public final Spinner durationSpin;

  @NonNull
  public final TextView durationlbl;

  @NonNull
  public final EditText enddateFld;

  @NonNull
  public final TextView enddatelbl;

  @NonNull
  public final EditText endpointFld;

  @NonNull
  public final TextView endpointlbl;

  @NonNull
  public final Guideline guideline;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final ImageButton insertbtn;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView picelbl;

  @NonNull
  public final ImageButton picturebtn;

  @NonNull
  public final TextView picturelbl;

  @NonNull
  public final EditText priceFld;

  @NonNull
  public final SeekBar riskBar;

  @NonNull
  public final TextView risklbl;

  @NonNull
  public final EditText startdateFld;

  @NonNull
  public final TextView startdatelbl;

  @NonNull
  public final EditText startpointFld;

  @NonNull
  public final TextView startpointlbl;

  @NonNull
  public final EditText titleFld;

  @NonNull
  public final TextView titlelbl;

  @NonNull
  public final Spinner typeSpin;

  @NonNull
  public final TextView typelbl;

  private ActivityAddTripBinding(@NonNull ScrollView rootView, @NonNull EditText descriptionFld,
      @NonNull TextView descriptionlbl, @NonNull EditText destinationFld,
      @NonNull TextView destinationlbl, @NonNull Spinner durationSpin,
      @NonNull TextView durationlbl, @NonNull EditText enddateFld, @NonNull TextView enddatelbl,
      @NonNull EditText endpointFld, @NonNull TextView endpointlbl, @NonNull Guideline guideline,
      @NonNull Guideline guideline2, @NonNull Guideline guideline3, @NonNull ImageView imageView,
      @NonNull ImageButton insertbtn, @NonNull ConstraintLayout main, @NonNull TextView picelbl,
      @NonNull ImageButton picturebtn, @NonNull TextView picturelbl, @NonNull EditText priceFld,
      @NonNull SeekBar riskBar, @NonNull TextView risklbl, @NonNull EditText startdateFld,
      @NonNull TextView startdatelbl, @NonNull EditText startpointFld,
      @NonNull TextView startpointlbl, @NonNull EditText titleFld, @NonNull TextView titlelbl,
      @NonNull Spinner typeSpin, @NonNull TextView typelbl) {
    this.rootView = rootView;
    this.descriptionFld = descriptionFld;
    this.descriptionlbl = descriptionlbl;
    this.destinationFld = destinationFld;
    this.destinationlbl = destinationlbl;
    this.durationSpin = durationSpin;
    this.durationlbl = durationlbl;
    this.enddateFld = enddateFld;
    this.enddatelbl = enddatelbl;
    this.endpointFld = endpointFld;
    this.endpointlbl = endpointlbl;
    this.guideline = guideline;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.imageView = imageView;
    this.insertbtn = insertbtn;
    this.main = main;
    this.picelbl = picelbl;
    this.picturebtn = picturebtn;
    this.picturelbl = picturelbl;
    this.priceFld = priceFld;
    this.riskBar = riskBar;
    this.risklbl = risklbl;
    this.startdateFld = startdateFld;
    this.startdatelbl = startdatelbl;
    this.startpointFld = startpointFld;
    this.startpointlbl = startpointlbl;
    this.titleFld = titleFld;
    this.titlelbl = titlelbl;
    this.typeSpin = typeSpin;
    this.typelbl = typelbl;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddTripBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddTripBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_trip, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddTripBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.descriptionFld;
      EditText descriptionFld = ViewBindings.findChildViewById(rootView, id);
      if (descriptionFld == null) {
        break missingId;
      }

      id = R.id.descriptionlbl;
      TextView descriptionlbl = ViewBindings.findChildViewById(rootView, id);
      if (descriptionlbl == null) {
        break missingId;
      }

      id = R.id.destinationFld;
      EditText destinationFld = ViewBindings.findChildViewById(rootView, id);
      if (destinationFld == null) {
        break missingId;
      }

      id = R.id.destinationlbl;
      TextView destinationlbl = ViewBindings.findChildViewById(rootView, id);
      if (destinationlbl == null) {
        break missingId;
      }

      id = R.id.durationSpin;
      Spinner durationSpin = ViewBindings.findChildViewById(rootView, id);
      if (durationSpin == null) {
        break missingId;
      }

      id = R.id.durationlbl;
      TextView durationlbl = ViewBindings.findChildViewById(rootView, id);
      if (durationlbl == null) {
        break missingId;
      }

      id = R.id.enddateFld;
      EditText enddateFld = ViewBindings.findChildViewById(rootView, id);
      if (enddateFld == null) {
        break missingId;
      }

      id = R.id.enddatelbl;
      TextView enddatelbl = ViewBindings.findChildViewById(rootView, id);
      if (enddatelbl == null) {
        break missingId;
      }

      id = R.id.endpointFld;
      EditText endpointFld = ViewBindings.findChildViewById(rootView, id);
      if (endpointFld == null) {
        break missingId;
      }

      id = R.id.endpointlbl;
      TextView endpointlbl = ViewBindings.findChildViewById(rootView, id);
      if (endpointlbl == null) {
        break missingId;
      }

      id = R.id.guideline;
      Guideline guideline = ViewBindings.findChildViewById(rootView, id);
      if (guideline == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.insertbtn;
      ImageButton insertbtn = ViewBindings.findChildViewById(rootView, id);
      if (insertbtn == null) {
        break missingId;
      }

      id = R.id.main;
      ConstraintLayout main = ViewBindings.findChildViewById(rootView, id);
      if (main == null) {
        break missingId;
      }

      id = R.id.picelbl;
      TextView picelbl = ViewBindings.findChildViewById(rootView, id);
      if (picelbl == null) {
        break missingId;
      }

      id = R.id.picturebtn;
      ImageButton picturebtn = ViewBindings.findChildViewById(rootView, id);
      if (picturebtn == null) {
        break missingId;
      }

      id = R.id.picturelbl;
      TextView picturelbl = ViewBindings.findChildViewById(rootView, id);
      if (picturelbl == null) {
        break missingId;
      }

      id = R.id.priceFld;
      EditText priceFld = ViewBindings.findChildViewById(rootView, id);
      if (priceFld == null) {
        break missingId;
      }

      id = R.id.riskBar;
      SeekBar riskBar = ViewBindings.findChildViewById(rootView, id);
      if (riskBar == null) {
        break missingId;
      }

      id = R.id.risklbl;
      TextView risklbl = ViewBindings.findChildViewById(rootView, id);
      if (risklbl == null) {
        break missingId;
      }

      id = R.id.startdateFld;
      EditText startdateFld = ViewBindings.findChildViewById(rootView, id);
      if (startdateFld == null) {
        break missingId;
      }

      id = R.id.startdatelbl;
      TextView startdatelbl = ViewBindings.findChildViewById(rootView, id);
      if (startdatelbl == null) {
        break missingId;
      }

      id = R.id.startpointFld;
      EditText startpointFld = ViewBindings.findChildViewById(rootView, id);
      if (startpointFld == null) {
        break missingId;
      }

      id = R.id.startpointlbl;
      TextView startpointlbl = ViewBindings.findChildViewById(rootView, id);
      if (startpointlbl == null) {
        break missingId;
      }

      id = R.id.titleFld;
      EditText titleFld = ViewBindings.findChildViewById(rootView, id);
      if (titleFld == null) {
        break missingId;
      }

      id = R.id.titlelbl;
      TextView titlelbl = ViewBindings.findChildViewById(rootView, id);
      if (titlelbl == null) {
        break missingId;
      }

      id = R.id.typeSpin;
      Spinner typeSpin = ViewBindings.findChildViewById(rootView, id);
      if (typeSpin == null) {
        break missingId;
      }

      id = R.id.typelbl;
      TextView typelbl = ViewBindings.findChildViewById(rootView, id);
      if (typelbl == null) {
        break missingId;
      }

      return new ActivityAddTripBinding((ScrollView) rootView, descriptionFld, descriptionlbl,
          destinationFld, destinationlbl, durationSpin, durationlbl, enddateFld, enddatelbl,
          endpointFld, endpointlbl, guideline, guideline2, guideline3, imageView, insertbtn, main,
          picelbl, picturebtn, picturelbl, priceFld, riskBar, risklbl, startdateFld, startdatelbl,
          startpointFld, startpointlbl, titleFld, titlelbl, typeSpin, typelbl);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
