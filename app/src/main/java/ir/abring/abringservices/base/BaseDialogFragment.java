package ir.abring.abringservices.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ir.abring.abringservices.R;

public abstract class BaseDialogFragment extends DialogFragment {

    protected Unbinder unbinder;

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeView();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getContentViewId(), null);
        Dialog dialog = new Dialog(getActivity(), R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);

        unbinder = ButterKnife.bind(this, view);
        initViews(view);
        return dialog;
    }

    protected abstract int getContentViewId();

    protected abstract void initBeforeView();

    protected abstract void initViews(View view);
}
