package com.xhl.C01_Java_Core.EffectiveJava.C06EnumsandAnnotations;

/**
 * @Author: xhl
 * @Date: 2026-07-01 09:07
 * @Description: 游戏角色与技能系统 RPG System
 */
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

// ================= 条目34 & 35：用枚举代替整型常量，用实例字段代替序数 =================
// 错误示范：public static final int SWORD = 0, BOW = 1; (魔法数字，极易出错)
// 错误示范：使用 ordinal() 获取索引 (enum.ordinal())，一旦调整顺序，索引就会错乱。
// 正确做法：给枚举绑定专属的实例字段。
enum WeaponType {
    SWORD(10, "Melee"),
    BOW(8, "Ranged"),
    STAFF(12, "Magic");

    private final int damage;
    private final String category;

    WeaponType(int damage, String category) {
        this.damage = damage;
        this.category = category;
    }

    public int getDamage() { return damage; }
}

enum BasicMath implements Operation {
    PLUS {
        @Override public int apply(int a, int b) { return a + b; }
    },
    MINUS {
        @Override public int apply(int a, int b) { return a - b; }
    }
}

// 未来如果需要扩展，只需新增一个枚举类实现 Operation 接口即可，无需修改原有代码。
enum AdvancedMath implements Operation {
    MULTIPLY {
        @Override public int apply(int a, int b) { return a * b; }
    }
}

// ================= 条目38：用接口模拟可扩展的枚举 =================
// 枚举不能被继承，如果我们需要一个“可扩展”的类型体系（比如不同的支付渠道、操作码），
// 最佳实践是：定义一个接口，让枚举去实现它。
interface Operation {
    int apply(int a, int b);
}

// 自定义注解
@interface TestMethod {
    String description() default "No description";
}

// ================= 条目41：用标记接口定义类型 =================
// 标记接口（没有方法的接口）比标记注解更好，因为它能定义一种“类型”。
// 比如：Serializable, Cloneable。
// 优势：可以在编译期通过类型检查（如方法参数要求必须是 Serializable），而注解做不到。
interface DatabaseEntity {} // 标记接口：表示这是一个可以存入数据库的实体

// ================= 条目39 & 40：注解优先于命名模式 & 始终使用 @Override =================
// 错误示范：用方法名 testStart() 来标记测试方法（命名模式），一旦拼错编译器不会报错。
// 正确做法：使用自定义注解，并在重写方法时永远加上 @Override。

// ================= 条目36：用 EnumSet 代替位域 =================
// 错误示范：用 int flags = FLAG_A | FLAG_B; (可读性差，易出错)
// 正确做法：使用 EnumSet，它底层基于位运算，但对外提供完美的类型安全集合接口。
class Player {
    private final Set<WeaponType> unlockedWeapons = EnumSet.noneOf(WeaponType.class);

    public void unlockWeapon(WeaponType type) {
        unlockedWeapons.add(type);
    }

    public boolean hasWeapon(WeaponType type) {
        return unlockedWeapons.contains(type);
    }
}

// ================= 条目37：用 EnumMap 代替序数索引 =================
// 错误示范：Object[] stats = new Object[WeaponType.values().length]; stats[0] = "Sword";
// 正确做法：使用 EnumMap，它内部用数组实现，性能极高，且完全类型安全。
class WeaponStats {
    private final Map<WeaponType, String> descriptions = new EnumMap<>(WeaponType.class);

    public WeaponStats() {
        descriptions.put(WeaponType.SWORD, "A sharp blade.");
        descriptions.put(WeaponType.BOW, "Shoots arrows.");
        descriptions.put(WeaponType.STAFF, "Casts spells.");
    }

    public String getDescription(WeaponType type) {
        return descriptions.getOrDefault(type, "Unknown weapon");
    }
}

class MathTest {
    // 始终使用 @Override，如果父类方法签名变了，编译器会立刻报错，防止隐蔽的 Bug。
    @Override
    public String toString() {
        return "MathTest";
    }

    // 用注解代替命名模式，清晰明了，且可以通过反射进行自动化处理。
    @TestMethod(description = "测试加法")
    public void testAdd() {
        System.out.println("Testing addition...");
    }
}

class UserEntity implements DatabaseEntity {}
class ConfigEntity implements DatabaseEntity {}

class Database {
    // 编译器会强制要求传入的对象必须是 DatabaseEntity 类型
    public void save(DatabaseEntity entity) {
        System.out.println("Saving entity to DB...");
    }
}