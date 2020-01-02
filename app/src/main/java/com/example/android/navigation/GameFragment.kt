/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    data class Question(
            val text: String,
            val answers: List<String>)

    // The first answer is the correct one. We randomize the answers before showing the text.
    // All questions must have four answers. We'd want these to contain references to string
    // resources so we could internationalize.(or better yet, not define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
            Question(text = "Which of the following is correct about C#?",
                    answers = listOf("All of these", "C# is designed for Common Language Infrastructure (CLI)", "C# was developed by Anders Hejlsberg and his team during the development of .Net Framework", "modern, general-purpose, object-oriented programming language developed by Microsoft")),
            Question(text = "Which of the following is correct about Object Types in C#?",
                    answers = listOf("All of these", "The Object Type is the ultimate base class for all data types in C# Common Type System (CTS)", "Object is an alias for System.Object class", "The object types can be assigned values of any other types, value types, reference types, predefined or user-defined types")),
            Question(text = "Which of the following converts a type to a small floating point number in C#?",
                    answers = listOf("ToSingle", "ToInt32", "ToSbyte", "ToInt64")),
            Question(text = "Which of the following operators returns the address of a variable in C#?",
                    answers = listOf("&", "*", "typeof", "sizeof")),
            Question(text = "Which of the following access specifiers in C# allow a class to expose its member variables and member functions to other functions and objects in the current assembly?",
                    answers = listOf("Internal", "Protected", "Private", "Public")),
            Question(text = "Which of the following is correct about the null coalescing operator in C#?",
                    answers = listOf("All of these", "The null coalescing operator is used with the nullable value types and reference types", "It is used for converting an operand to the type of another nullable (or not) value type operand, where an implicit conversion is possible", "If the value of the first operand is null, then the operator returns the value of the second operand, otherwise it returns the value of the first operand")),
            Question(text = "Which of the following is correct about static member variables of a class?",
                    answers = listOf("Both of the these", "None of these", "When we declare a member of a class as static, it means no matter how many objects of the class are created, there is only one copy of the static member", "We can define class members variables as static using the static keyword")),
            Question(text = "Dynamic polymorphism is implemented by abstract classes and virtual functions.",
                    answers = listOf("True", "False")),
            Question(text = "Which of the following preprocessor directives specifies the end of a conditional directive in C#?",
                    answers = listOf("endif", "elif", "if", "else")),
            Question(text = "Which of the following preprocessor directives marks the end of a #region block in C#?",
                    answers = listOf("endregion", "error", "region", "warning")),
            Question(text = "The ‘ref’ keyword can be used with which among the following?",
                    answers = listOf("Static function/subroutine", "Static data", "Instance function/subroutine", "All of these")),
            Question(text = "To implement delegates, what is the necessary condition?",
                    answers = listOf("Class Declaration", "Inheritance", "Runtime Polymorphism", "Exceptions")),
            Question(text = "Suppose a Generic class called 'SortObjects' is to be made capable of sorting objects of any type(integer, single, byte etc). Which of the following programming constructs is able to implement the comparison function?",
                    answers = listOf("Delegate", "Attribute", "Encapsulation", "Interface")),
            Question(text = "Which of these statements regarding Delegates is false?",
                    answers = listOf("None of these", "Delegates are object oriented", "Delegates are type safe", "Delegates are of reference types")),
            Question(text = "Which keyword is used to declare a base class method while performing overriding of base class methods?",
                    answers = listOf("virtual", "this", "extend", "override")),
            Question(text = "The process of defining a method in a subclass having the same name and type signature as a method in its superclass is known as what?",
                    answers = listOf("Method overriding", "Method overloading", "Method hiding", "None of these")),
            Question(text = "Which of the given modifiers can be used to prevent Method overriding?",
                    answers = listOf("Sealed", "Constant", "Static", "final")),
            Question(text = "Which statement is true?",
                    answers = listOf("When overriding a method, the names and type signatures of the override method must be the same as the virtual method that is being overridden", "We can override virtual as well as nonvirtual methods", "Abstract methods can be a virtual method", "Static methods can be a virtual method")),
            Question(text = "Which of the following cannot be used to declare a class as virtual?",
                    answers = listOf("Fields", "Events", "Properties", "Methods")),
            Question(text = "What is the modifier used to hide the base class methods?",
                    answers = listOf("New", "Override", "Sealed", "Virtual")),
            Question(text = "To override a method in the subclass, the base class method should be defined as what?",
                    answers = listOf("All of these", "Virtual", "Abstract", "Override")),
            Question(text = "The capability of an object in C# to take a number of different forms and hence display behaviour as according is known as what?",
                    answers = listOf("Polymorphism", "Abstraction", "Encapsulation", "None of these")),
            Question(text = "Which of the following keywords is used to change data and behavior of a base class by replacing a member of the base class with a new derived member?",
                    answers = listOf("new", "base", "Overrides", "Overloads")),
            Question(text = "The number of levels of inheritance are?",
                    answers = listOf("4", "3", "2", "5")),
            Question(text = "In an inheritance chain, through which of the following are the base class and its components accessible to the derived class?",
                    answers = listOf("Class visibility modifiers (public,private etc.)", "Scope resolution operator(:)", "Dot operator (.)", "All of these")),
            Question(text = "What are the class visibility modifiers among the following?",
                    answers = listOf("Private, protected, public, internal, protected internal", "Private, protected, public", "All of these", "Private, protected, public, internal")),
            Question(text = "In the concept of Inheritance, which of the following members of the base class are accessible to derived class members?",
                    answers = listOf("Protected", "Static", "Private", "Shared")),
            Question(text = "A class member that has been declared protected becomes a member of a subclass of which type?",
                    answers = listOf("Static Member", "Protected Member", "Private Member", "Public Member")),
            Question(text = "Which functionality is facilitated by the inheritance mechanism?",
                    answers = listOf("All of these", "Implements new functionality in derived class", "Override the existing functionality of base class", "Use the existing functionality of base class")),
            Question(text = "Which is the correct way to create an object of the given class 'abc'?",
                    answers = listOf("Declaring existing class as sealed", "Declaring existing class as shadows", "Declaring existing class as overloads", "Declaring existing class as override")),
            Question(text = "Which form of inheritance is not supported directly by C# .NET?",
                    answers = listOf("Multiple inheritance", "Multilevel inheritance", "Single inheritance", "Hierarchical inheritance")),
            Question(text = "If no access modifier for a class is specified, then class accessibility is defined as...?",
                    answers = listOf("Private", "Internal", "Protected", "Public")),
            Question(text = "Which of these is used as a default specifier for a member of a class if no access specifier is used for it?",
                    answers = listOf("Private", "Public", "Public, within its own class", "Protected")),
            Question(text = "Which of these is used to access members of a class before the object of that class is created?",
                    answers = listOf("Static", "Protected", "Private", "Public")),
            Question(text = "Which of these base classes are accessible to the derived class members?",
                    answers = listOf("Protected", "Static", "Private", "Shared")),
            Question(text = "What is the process by which we can control parts of a program that can access the members of a class?",
                    answers = listOf("Encapsulation", "Abstraction", "Recursion", "Polymorphism")),
            Question(text = "Which of these access specifiers must be used for the main() method?",
                    answers = listOf("Private", "Public", "Protected", "None of these")),
            Question(text = "What is the process of defining two or more methods within the same class that have the same name but different parameters lists?",
                    answers = listOf("Method overloading", "None of these", "Encapsulation", "Method overriding")),
            Question(text = "Which of these can be overloaded?",
                    answers = listOf("Both Constructors and Methods", "Constructors", "Methods", "None of these")),
            Question(text = "What is the process of defining a method in terms of itself, that is a method that calls itself?",
                    answers = listOf("Recursion", "Encapsulation", "Abstraction", "Polymorphism")),
            Question(text = "Which keyword is used to refer the baseclass constructor to the subclass constructor?",
                    answers = listOf("Base", "Static", "This", "Extend")),
            Question(text = "When we call a constructor method among different given constructors we match the suitable constructor by matching the name of constructor first, then the number and then the type of parameters to decide which constructor is to be overloaded. What is this process called?",
                    answers = listOf("Polymorphism", "Encapsulation", "Inheritance", "Method overriding")),
            Question(text = "A type of class which does not have its own objects but acts as a base class for its subclass is known as what?",
                    answers = listOf("Abstract class", "Static class", "Sealed class", "None of these")),
            Question(text = "If a class inheriting an abstract class does not define all of its functions then it is known as what?",
                    answers = listOf("Abstract", "None of these", "Static class", "A simple class")),
            Question(text = "Which of the following modifiers is used when an abstract method is redefined by a derived class?",
                    answers = listOf("Override", "Overloads", "Base", "Virtual")),
            Question(text = "Which of the following cannot be used to declare an interface correctly?",
                    answers = listOf("Structures", "Methods", "Events", "Properties")),
            Question(text = "A class consists of two interfaces with each interface consisting of three methods. If the class has no instance data, which of the following indicates the correct size of the object created from this class?",
                    answers = listOf("24 bytes", "0 bytes", "16 bytes", "12 bytes")),
            Question(text = "Which of the following statements correctly define the implementation of interface?",
                    answers = listOf("The calls to implementation of interface methods are routed through a method table", "A class which implements an interface can explicitly implement members of that interface", "None of these", "One interface can be implemented in another interface")),
            Question(text = "Which statement is true?",
                    answers = listOf("Properties could be declared inside an interface", "None of these", "One class could implement only one interface", "Interfaces cannot be inherited")),
            Question(text = "Which keyword is used for the correct implementation of an interface in C#.NET?",
                    answers = listOf("interface", "Intf", "intf", "Interface")),
            Question(text = "What makes interfaces different from classes?",
                    answers = listOf("All of these", "Interfaces consists of only declaration but not implementation", "Interfaces cannot be used directly like classes to create new objects", "Interfaces consist of declarations of methods, properties events and type definitions")),
            Question(text = "Which of the following is the correct way of implementing an interface addition by class maths?",
                    answers = listOf("class maths : addition {}", "None of these", "class maths imports addition {}", "class maths implements addition {}")),
            Question(text = "Which of these can be used to fully abstract a class from its implementation?",
                    answers = listOf("Interfaces", "Objects", "Packages", "None of these")),
            Question(text = "Access specifiers which can be used for an interface are?",
                    answers = listOf("Public", "Protected", "Private", "All of these")),
            Question(text = "Which of the following keywords are used to overload user defined types by defining static member functions?",
                    answers = listOf("operator", "op", "opoverload", "operatoroverload")),
            Question(text = "Which operators can be overloaded?",
                    answers = listOf("+", "[]", "+=", "||")),
            Question(text = "What is the correct method to define the '+' operator?",
                    answers = listOf("public static sample operator +(int a, int b)", "public abstract operator +(int a, int b)", "public sample operator +(int a, int b)", "public abstract sample operator +(int a, int b)")),
            Question(text = "What is the vector in operator overloading?",
                    answers = listOf("Data Type", "None of these", "method()", "class")),
            Question(text = "What is Recursion in C# defined as?",
                    answers = listOf("A process of defining a method that calls itself repeatedly", "A process of defining a method that calls other methods which in turn calls this method", "Another process of defining a method that calls other methods repeatedly", "Another form of class")),
            Question(text = "Which of these will happen if the recursive method does not have a base case?",
                    answers = listOf("Infinite loop condition occurrence", "None of these", "After 10000 executions program will be automatically stopped", "System gets hanged")),
            Question(text = "Which of these statements are false?",
                    answers = listOf("Recursion is always managed by C# Runtime environment", "A recursive method must have a base case", "Recursion always uses stack", "Recursive methods are faster than programmer written loop to call the function repeatedly using a stack")),
            Question(text = "Which of these data types is used by the operating system to manage the Recursion in C#",
                    answers = listOf("Stack", "Tree", "Array", "Queue")),
            Question(text = "Choose the namespace in which the interface IEnumerable is declared.",
                    answers = listOf("System.Collections.Generic", "System.Collections", "None of these", "Both System.Collections and System.Collections.Generic")),
            Question(text = "Can we use LINQ to query against a DataTable?",
                    answers = listOf("No", "Yes", "It's pronounced Data", "None of these")),
            Question(text = "Select the namespace which should be included while making use of LINQ operations.",
                    answers = listOf("System.Linq", "System.Collections.Generic", "System.Text", "None of these")),
            Question(text = "Which of the following string() methods are used to compare two strings with each other?",
                    answers = listOf("Copy()", "Compare()", "CompareTo()", "CopyTo()")),
            Question(text = "Choose the base class for string() method.",
                    answers = listOf("System.String", "System.Array", "System.char", "None of these")),
            Question(text = "Which of the following is true about a string in C#.NET?",
                    answers = listOf("A string has a zero-based index", "A number cannot be represented in the form of a string", "A string is mutable because it can be modified once it has been created", "The System.Array class is used to represent a string")),
            Question(text = "What is the correct way to find if the contents of two strings are equal?",
                    answers = listOf("if (strcmp (s1 ,s2))", "if (s1 = s2)", "if (s1 != s2)", "if ( s1 is s2)")),
            Question(text = "Which of these operators can be used to concatenate two or more String objects?",
                    answers = listOf("+", "+=", "&", "||")),
            Question(text = "What method is used to remove white spaces from a string?",
                    answers = listOf("Trim()", "TrimStart()", "Substring()", "Split()")),
            Question(text = "Which of these classes provide the operation of reading from and writing to the console in C#.NET?",
                    answers = listOf("System.Console", "System.Array", "System.Output", "System.ReadLine")),
            Question(text = "Which of the given stream methods provide access to the output console by default in C#.NET?",
                    answers = listOf("Console.Out", "Console.Error", "Console.In", "All of these")),
            Question(text = "Which of the given stream methods provide access to the input console in C#.NET?",
                    answers = listOf("Console.In", "All of these", "Console.Out", "Console.Error")),
            Question(text = "How many input methods are defined by the stream method Console.In in C#.NET?",
                    answers = listOf("3", "4", "5", "1")),
            Question(text = "Select the correct methods provided by Console.In?",
                    answers = listOf("Read(), ReadLine(), ReadKey()", "Read(), ReadLine()", "ReadKey(), ReadLine()", "ReadKey(), ReadLine()")),
            Question(text = "Choose the object of TextReader class.",
                    answers = listOf("Console.In", "Console.Out", "Console.Error", "None of these")),
            Question(text = "Choose the object(s) defined by the Textwriter class.",
                    answers = listOf("Console.Error", "None of these", "Console", "Console.In")),
            Question(text = "Choose the method(s) provided by Console.Out and Console.Error.",
                    answers = listOf("Write & WriteLine", "Write", "WriteLine", "WriteKey")),
            Question(text = "Which of these methods are used to read single character from the console?",
                    answers = listOf("read()", "readLine()", "getline()", "get()")),
            Question(text = "Which among the following methods are used to write characters to a string?",
                    answers = listOf("StringWriter", "None of these", "StreamReader", "StreamWriter")),
            Question(text = "Which method in Console enables to read individual inputs directly from the keyboard in a non line-buffered manner?",
                    answers = listOf("ReadKey()", "ReadLine()", "Read()", "All of these")),
            Question(text = "What is the output returned by Console if ReadLine() stores I/O error?",
                    answers = listOf("I/O EXCEPTION ERROR", "False", "0", "1")),
            Question(text = "Name the method(s) used to read byte streams from the file?",
                    answers = listOf("ReadByte()", "Read", "Readkey()", "KeyByte()")),
            Question(text = "Which of the following represents the process of defining two or more methods within the same class having the same name but different parameters lists?",
                    answers = listOf("Method overloading", "None of these", "Encapsulation", "Method overriding")),
            Question(text = "Which of the following keywords can be used to overload user-defined types by defining static member functions?",
                    answers = listOf("operatoroverload", "opoverload", "operator", "udoperator")),
            Question(text = "Which of the following represents the process where a method in a subclass has same name & type signature as a method in its superclass?",
                    answers = listOf("Method overriding", "None of these", "Method overloading", "Method hiding")),
            Question(text = "Which of the following modifiers can be used to prevent Method overriding?",
                    answers = listOf("Sealed", "Constant", "Static", "final"))
    )

    //Questions Template

    //      Question(text = "",
    //              answers = listOf("", "", "", ""))



    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
                inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnswerRadioButton -> answerIndex = 1
                    R.id.thirdAnswerRadioButton -> answerIndex = 2
                    R.id.fourthAnswerRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numQuestions,questionIndex))
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment2())
                }
            }
        }
        return binding.root
    }

    // randomize the questions and set the first question
    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}
