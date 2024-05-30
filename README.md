<<<<<<< Updated upstream

## Exception in Application start method

java.lang.reflect.InvocationTargetException

1. 結構問題
   package位置須放置在相對應名稱

2. 檔名必須是絕對位置，問題出現在使用 FXMLLoader 類載入 XML 物件的引數位置，但它返回一個空值。簡單來說，getResource()
   方法不會定位函式引數中提供的路徑。須給絕對路徑

又分為兩種寫法
fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
Scene scene = new Scene(root);

3. controller問題
   在xml檔案內controller必須fully qualified name(包含package位置)

fx:controller="application.DrawLinesController"

4. VM設置
   需在虛擬機的參數做調整
   --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml

https://blog.51cto.com/u_16099190/6331597
https://www.cnblogs.com/stars-one/p/10959869.html
=======
Exception in Application start method

> > > > > > > Stashed changes
