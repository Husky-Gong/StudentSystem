package SystemClass;

import SystemUtil.SystemAnnotation;

@SystemAnnotation(tableName = "student")
public class Student {
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setId",
			getMethod = "getId",
			columnName = "student_id"
			)
	int id;
	
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setName",
			getMethod = "getName",
			columnName = "student_name"
			)
	String name;
	
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setSex",
			getMethod = "getSex",
			columnName = "student_sex"
			)
	String sex;
	
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setAge",
			getMethod = "getAge",
			columnName = "student_age"
			)
	int age;
	
	@SystemAnnotation(
			readOnly = false,
			setMethod = "setScore",
			getMethod = "getScore",
			columnName = "student_score"
			)
	double score;

	public int getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = (int) id;
	}

	public String getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = (String) name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(Object sex) {
		this.sex = (String) sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(Object age) {
		this.age = (int) age;
	}

	public double getScore() {
		return score;
	}

	public void setScore(Object score) {
		this.score = (double) score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + ", age=" + age + ", score=" + score + "]";
	}

	public Student(int id, String name, String sex, int age, double score) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.score = score;
	}

	public Student() {}
	
	
}
/*
 * create table student(
	student_id int not null auto_increment,
	student_name varchar(20) not null,
	student_sex varchar(10) not null,
	student_age int not null,
	student_score double not null,
	primary key (student_id)
);
*/