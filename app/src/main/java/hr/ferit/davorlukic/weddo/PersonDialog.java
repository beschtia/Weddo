package hr.ferit.davorlukic.weddo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class PersonDialog extends DialogFragment {

    private static final String TAG = "PersonDialog";

    public interface OnInputListener{
        void sendInput(String name, String lastName);
    }

    public OnInputListener mOnInputListener;
    private TextView mActionCancel;
    private TextView mActionOk;
    private EditText mInputName;
    private EditText mInputLastName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_guest, container, false);
        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        mInputName = view.findViewById(R.id.etInputName);
        mInputLastName = view.findViewById(R.id.etInputLastName);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mInputName.getText().toString();
                String lastname = mInputLastName.getText().toString();

                mOnInputListener.sendInput(name,lastname);

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (OnInputListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach ClassCastException: " + e.getMessage());
        }
    }
}
