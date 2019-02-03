package hr.ferit.davorlukic.weddo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class SummaryDialog extends DialogFragment {

    private TextView mActionOk, mNumber, mTotal, mPayed, mLeft;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_summary, container, false);

        mActionOk = view.findViewById(R.id.actionSummary_ok);
        mNumber = view.findViewById(R.id.tvNumber);
        mTotal = view.findViewById(R.id.tvTotal);
        mPayed = view.findViewById(R.id.tvPayed);
        mLeft = view.findViewById(R.id.tvLeft);

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUp();
    }

    private void setUp() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Person> persons=realm.where(Person.class).findAll();

        Double total=(realm.where(Group.class).like("id", "flower").findFirst()).getPrice() +
                (realm.where(Group.class).like("id", "band").findFirst()).getPrice() +
                (realm.where(Group.class).like("id", "photo").findFirst()).getPrice() +
                (realm.where(Group.class).like("id", "hall").findFirst()).getPrice() * persons.size();

        Double payed = (realm.where(Group.class).like("id", "flower").findFirst()).getDownPayment() +
                (realm.where(Group.class).like("id", "band").findFirst()).getDownPayment() +
                (realm.where(Group.class).like("id", "photo").findFirst()).getPrice() +
                (realm.where(Group.class).like("id", "hall").findFirst()).getDownPayment();

        mNumber.setText(String.valueOf(persons.size()));
        mTotal.setText(String.format("%.2f", total));
        mPayed.setText(String.format("%.2f", payed));
        mLeft.setText(String.format("%.2f", total-payed));
    }
}
