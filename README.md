# MongoDB property polymorphism

```kotlin
@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.EXISTING_PROPERTY,
	property = "editorType"
)
@JsonSubTypes(
	JsonSubTypes.Type(value = TitleA::class),
	JsonSubTypes.Type(value = TitleB::class),
	JsonSubTypes.Type(value = Image::class)
)
interface EditorContent {
	fun getEditorType(): EditorType
}
//해당 대상들은 spring-mongo-data에는 필요가 없음.
//만약 RequestBody등으로 받아들일때는 필요로 함.

```

@JsonTypeInfo와 @JsonSubType는 몽고db에서는 이용치 않음

MongoDB는 typeAlias 또는 없을시 시용한 _class를 통해서 데이터에 접근하며
만약 _class가 없는 경우 일치하는 프로퍼티로만 접근할 수 있음.

인테페이스에는 지정이 필요 없으나 서브타입에는 해당 인터페이스가 사용되는 document를 명시해줘야
TypeAlias를 스캔함

reference: https://github.com/spring-projects/spring-data-mongodb/issues/3321

만약 SubType에 document지정을 안해주면 interface(또는 abstract class) instantiate를 실패했다는 오류가 발생함.

부모타입에도 property를 가지고 싶으면 class를 사용해야하며(data class는 모든 생성자에 들어간 것이 property로 선언됨) 이를 상속하는 구조로 가지면된다. 또 조회할때는 super Type을 통해 조회하면 sub Type으로 돌려준다.