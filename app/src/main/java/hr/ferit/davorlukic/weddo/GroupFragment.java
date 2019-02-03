package hr.ferit.davorlukic.weddo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;

    private EditText mName, mDownPayment, mPrice, mPhone, mNote;
    private Button mSetName, mSetDownPayment, mSetPrice, mSetPhone, mSetNote;
    private TextView tvPrice;

    public GroupFragment() {
        // Required empty public constructor
    }

    public static GroupFragment newInstance(String param1) {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mName = view.findViewById(R.id.etName);
        mDownPayment = view.findViewById(R.id.etDownPayment);
        mPrice = view.findViewById(R.id.etPrice);
        mPhone = view.findViewById(R.id.etPhone);
        mNote = view.findViewById(R.id.etNote);
        mSetName = view.findViewById(R.id.btnSetName);
        mSetDownPayment = view.findViewById(R.id.btnSetDownPayment);
        mSetPrice = view.findViewById(R.id.btnSetPrice);
        mSetPhone = view.findViewById(R.id.btnSetPhone);
        mSetNote = view.findViewById(R.id.btnSetNote);
        tvPrice = view.findViewById(R.id.tvPrice);

        setUpText();
        setUpListeners();

    }

    private void setUpText() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Group> results = realm.where(Group.class).like("id", mParam1).findAll();
        if (results.size()==0){
            Group group = new Group(mParam1);
            realm.beginTransaction();
            realm.copyToRealm(group);
            realm.commitTransaction();
        }

        Group result = realm.where(Group.class).like("id", mParam1).findFirst();

        if (mParam1.equalsIgnoreCase("hall")){
            tvPrice.setText(getString(R.string.price_per_person));
        }

        mName.setText(result.getName());
        mDownPayment.setText(result.getDownPayment().toString());
        mPrice.setText(result.getPrice().toString());
        mPhone.setText(result.getPhone());
        mNote.setText(result.getNote());
    }

    private void setUpListeners() {
        mSetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(mParam1,"btnName", mName.getText().toString());
            }
        });

        mSetDownPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mDownPayment.getText().toString();
                if (input.isEmpty()) {
                    input = "0";
                }
                mListener.onFragmentInteraction(mParam1,"btnDownPayment", input);
            }
        });

        mSetPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mPrice.getText().toString();
                if (input.isEmpty()) {
                    input = "0";
                }
                mListener.onFragmentInteraction(mParam1,"btnPrice", input);
            }
        });

        mSetNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(mParam1,"btnNote", mNote.getText().toString());
            }
        });

        mSetPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(mParam1, "btnPhone", mPhone.getText().toString());
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String fragment, String sender, String input);
    }

}
