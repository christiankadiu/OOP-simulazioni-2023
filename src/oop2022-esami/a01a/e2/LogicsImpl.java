package a01a.e2;
import java.util.ArrayList;
import java.util.List;

import a01a.e1.Pair;

public class LogicsImpl implements Logics {

    List<List<Boolean>> matrice;
    int size;
    int count = 0;

    LogicsImpl(int size){
        this.size = size;
        matrice = new ArrayList<>();
        for (int i = 0; i < size; i++){
            List<Boolean> raw = new ArrayList<>();
            matrice.add(raw);
            for (int k = 0; k < size; k++){
                raw.add(false);
            }
        }
    }

    void print(){
        for (int i = 0; i < size; i++){
            for (int k = 0; k < size; k++){
                System.out.print(matrice.get(i).get(k)+"\t");
            }
            System.out.print("\n");
        }
    }

    @Override
    public String get(Pair<Integer, Integer> pos) {
        if (matrice.get(pos.getX()).get(pos.getY())){
            matrice.get(pos.getX()).set(pos.getY(), false);
            this.count = 0;
            return "";
        }else{
            matrice.get(pos.getX()).set(pos.getY(), true);
            this.count++;
            print();
            System.out.println("il count corrente: "+this.count);
            return "*";
        }
    }

    @Override
    public boolean toQuit() {
        if ((checkDiagonalsLeft() || checkDiagonalsRight()) && this.count >= 3){
            return true;
        }
        return false;
    }


    private Boolean checkDiagonalsRight(){
        int t, j, c = 0;
        for (int i = (this.size - 1); i >= 0; i--){
            j = 0;
            t = i;
            c = 0;
            while (j < size && t >= 0){
                if (matrice.get(j).get(t)){
                    c++;
                }
                if (c == 3){
                    return true;
                }
                j++;
                t--;
            }
        }
        // for (int i = (this.size - 1); i > 0; i--){
        //     j = i;
        //     t = this.size - 1;
        //     c = 0;
        //     while (j > 0 && t > 0){
        //         if (matrice.get(j).get(t)){
        //             c++;
        //         }
        //         if (c == 3){
        //             return true;
        //         }
        //         j--;
        //         t--;
        //     }
        // }
        for (int i = (this.size - 1); i > 0; i--){
            j = i;
            t = this.size - 1;
            c = 0;
            while (j < size && t >= 0){
                if (matrice.get(j).get(t)){
                    c++;
                }
                if (c == 3){
                    return true;
                }
                j++;
                t--;
            }
        }
        
        return false;
    }


    private Boolean checkDiagonalsLeft(){
        int t, j, c = 0;
        for (int i = 0; i < this.size; i++){
            j = 0;
            t = i;
            c = 0;
            while (j < size && t < size){
                if (matrice.get(j).get(t)){
                    c++;
                }
                if (c == 3){
                    return true;
                }
                j++;
                t++;
            }
        }
        for (int i = 1; i < this.size; i++){
            j = i;
            t = 0;
            c = 0;
            while (j < size && t < size){
                if (matrice.get(j).get(t)){
                    c++;
                }
                if (c == 3){
                    return true;
                }
                j++;
                t++;
            }
        }
        return false;
    }
}