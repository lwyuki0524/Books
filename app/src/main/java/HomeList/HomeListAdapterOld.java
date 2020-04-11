package HomeList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.books.R;

import java.util.ArrayList;
import java.util.List;

public class HomeListAdapterOld extends RecyclerView.Adapter<HomeListAdapterOld.ViewHolder> {

    private Context context;
    private List<HomeList_card> activityList;

    public HomeListAdapterOld(Context context, ArrayList<HomeList_card> activityList) {
        this.context = context;
        this.activityList = activityList;
    }

    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeListAdapterOld.ViewHolder holder, int position) {
        final HomeList_card activity = activityList.get(position);
        holder.date.setText(activity.getdate());
        holder.expense.setText(activity.getTotalExp());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Adapter 需要一個 ViewHolder，只要實作它的 constructor 就好，保存起來的view會放在itemView裡面
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView expense;
        ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date_info);
            expense = (TextView) itemView.findViewById(R.id.total_exp);
        }
    }
}
