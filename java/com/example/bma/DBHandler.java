package com.example.bma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "bma_db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String USER_TABLE = "User";

    private static final String PRODUCT_TABLE = "Product";

    private static final String ADMIN_TABLE = "Admin";

    private static final String SALES_TABLE ="Sales";

    //User Section
    //U = User
    // below variable is for our id column.
    private static final String U_ID_COL = "id";

    // below variable is for our full name column
    private static final String U_FULLNAME_COL = "name";

    // below variable is for our  name column
    private static final String U_NAME_COL = "name";

    // below variable is for our  password column
    private static final String U_PASSWORD_COL = "password";

    //Admin Section
    // A = Admin
    private static final String A_ID_COL = "id";

    // below variable is for our course name column
    private static final String A_NAME_COL = "name";

    // below variable is for our password column
    private static final String A_PASSWORD_COL = "password";

    //Product Section
    // P = Product
    private static final String P_ID_COL = "id";

    private static final String P_NAME_COL = "productName";

    private static final String P_QUANTITY_COL = "productQuantity";

    private static final String P_PRICE_COL = "productPrice";

    //Sales Section
    //S = Sales
    private static final String S_ID_COL = "id";

    private static final String S_DATE_COL = "date";

    private static final String S_TOTAL_COL = "total";





    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table User(id INTEGER primary key ,name TEXT, password TEXT)");
        db.execSQL("create Table Admin(id INTEGER primary key,fullName TEXT, name TEXT, password TEXT)");
        db.execSQL("create Table Product(id INTEGER primary key,productName TEXT, productQuantity TEXT, productPrice TEXT)");
        db.execSQL("create Table Sales(id INTEGER primary key, date TEXT, total INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ADMIN_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SALES_TABLE);

        onCreate(db);
    }

    //Handle user data
    public boolean addUser(String fullName,String username,String password){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(U_FULLNAME_COL,fullName);
        values.put(U_NAME_COL, username);
        values.put(U_PASSWORD_COL, password);

        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(USER_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public Cursor readUser(String username, String password){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM User WHERE name = ? AND password =?", new String[]{username,password});

        return c;
    }

    boolean updateFullName(String id,String fullName){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_FULLNAME_COL, fullName);

        long result = db.update(USER_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateUserName(String id,String name){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_NAME_COL, name);

        long result = db.update(USER_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateUserPassword(String id,String password){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(U_PASSWORD_COL, password);

        long result = db.update(USER_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //Handle profit data
    public boolean addSales(String date,int total){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(S_DATE_COL, date);
        values.put(S_TOTAL_COL, total);

        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(SALES_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor readAllSales(){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM Sales order by date ASC", null);

        return c;
    }

    boolean updateSalesTotal(String id,int total){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(S_TOTAL_COL, total);

        long result = db.update(SALES_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    //Handle admin data
    public boolean addAdmin(String username,String password){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(A_NAME_COL, username);
        values.put(A_PASSWORD_COL, password);

        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(ADMIN_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor readAdmin(String username, String password){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM Admin WHERE name = ? AND password =?", new String[]{username,password});

        return c;
    }

    public boolean addProduct(Product product){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(P_NAME_COL,product.getProductName());
        values.put(P_QUANTITY_COL,product.getProductQuantity());
        values.put(P_PRICE_COL,product.getProductPrice());

        // after adding all values we are passing
        // content values to our table.
        long result = db.insert(PRODUCT_TABLE, null, values);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor readAllProduct(){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM Product order by productName ASC", null);

        return c;
    }

    public Cursor readProduct(String id){
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor c = db.rawQuery("SELECT * FROM Product WHERE id =?", new String[]{id});

        return c;
    }

    boolean updateProduct(String id,String name, String quantity, String price){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(P_NAME_COL, name);
        values.put(P_QUANTITY_COL, quantity);
        values.put(P_PRICE_COL, price);


        long result = db.update(PRODUCT_TABLE,values,"id=?",new String[]{id});


        if(result == -1){
            return false;
        }
        else{
            return true;
        }


    }

    boolean updateProductName(String id,String name){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(P_NAME_COL, name);

        long result = db.update(PRODUCT_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateProductQuantity(String id,int quantity){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(P_QUANTITY_COL, quantity);

        long result = db.update(PRODUCT_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    boolean updateProductPrice(String id,int price){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(P_PRICE_COL, price);

        long result = db.update(PRODUCT_TABLE,values,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }
    

    boolean deleteProduct(String id){

        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(PRODUCT_TABLE,"id=?",new String[]{id});

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }



}
