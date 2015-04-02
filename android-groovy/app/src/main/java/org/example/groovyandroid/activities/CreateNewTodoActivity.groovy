package org.example.groovyandroid.activities

import android.app.Activity
import org.example.groovyandroid.R
import org.example.groovyandroid.bean.ToDo

class CreateNewTodoActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart()
        setContentView(R.layout.create_todo)

//        def message = findViewById(R.id.message) as TextView

        def button = findViewById(R.id.newTodoButton)

        // use groovy multiple assignment to map view ids to variables
        def (title, description) = [R.id.newTitle, R.id.newDescription].collect(this.&findViewById)

        /*
           // in Java adding the OnClickListener would looks like this
           Button button = (Button) findViewById(R.id.newTodoButton);
           button.setOnClickListener(new View.OnClickListener() {
               @Override
               void onClick(View v) {
                   ToDoApplication app = (ToDoApplication) getApplication();
                   app.getToDos(new Todo(title.getText(), description.getText()));
               }
           })
       */
        button.onClickListener = {
            application.toDos << new ToDo(title.text.toString(), description.text.toString())
            finish()
        }
    }
}