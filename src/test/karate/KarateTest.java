package karate; // パッケージ名が karate になっていること

import com.intuit.karate.junit5.Karate;

class KarateTest {

    @Karate.Test
    Karate testAll() {
        // karateパッケージ内のすべての feature ファイルを実行する
        return Karate.run().relativeTo(getClass());
    }
}