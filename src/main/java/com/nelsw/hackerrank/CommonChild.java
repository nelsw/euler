package com.nelsw.hackerrank;

import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class CommonChild {

    static StringBuilder sb     = new StringBuilder();
    static int           result = 0;

    public static void main(String[] args) {

        resolve("HARRY", "SALLY", 2);
        System.out.println();

        resolve("SHINCHAN", "NOHARAAA", 3);
        System.out.println();

        var s1 = "ELGGYJWKTDHLXJRBJLRYEJWVSUFZKYHOIKBGTVUTTOCGMLEXWDSXEBKRZTQUVCJNGKKRMUUBACVOEQKBFFYBUQEMYNENKYYGUZSP";
        var s2 = "FRVIFOVJYQLVZMFBNRUTIYFBMFFFRZVBYINXLDDSVMPWSQGJZYTKMZIPEGMVOUQBKYEWEYVOLSHCMHPAZYTENRNONTJWDANAMFRX";
        resolve(s1, s2, 27);

        s1 = "VGXGPUAMKXKSZHKBPPHYKINKEZPLVFJAQMOPODOTKRJZRIMLVUMUARENEXCFYCEBEURGVJYOSPDHVUYFVTVNRDYLUACVRAYGGWNPNZIJDIFYERVJAOALCGXOVLDQFZAORAHDIGYOJKNVIAZTPCMXLVOVAFHJPHVSHYFIQQTQBXJJMQNGQJHWKCEXECMDKMZAKBZRKJWQDYUXDVOOSSJOATRYXMBWXBWEXNAGMAYGZYFNZPQFTOBTAOTUAYXMWVZLLKUJIDHUKZWZCLTGQNGGUFTUAHALWVJWQNCKSIZGZAJKHYJUJLKSESZAFZJMDTSBYLDHYLCGKYNGVMHNEQYJDUGOFKLITXAOYKFOQKZSZNJYARKUPRERIVHUBPEHXMOYDAKKLBDNFHFXAMOTUBELZVBOZJARAEFMLOTFTNQRJOLVUAMAHNDEKFDSQCFVMQBOCBOMJXRQSFSKEVFXPHCQOQKBBOMCYURWLRNHRHCTNTZLYLVWULBDKCDPPGYKICHJTPUKFNLXFCEVKJEZQSMEYCANJLBESSRFAZDPRCOMDPJIMSFBUSLKSYVEERGCGMONCTCSVYPOLPLCGSQYFKILRIXODIWQCYREIWKRPIUIASFKJEXPFTZNQIBLSRJUYFSKNDAPWJEFUCDQCIUEHVFNDGHRXXNMVZLJXIOYUNDVPNDABSBNWOEYOMRJDCQCRXVYAHERMUDCCMUEAHEBYVSAKXWSEQZDUYFEZUJAFFDRSQFSEQSDFCGDENMRFWFNDIJTEPXHNVEDFBAGZRXKPRTGBOUKFXIWHFZFKSNAWGCUBSPXSIUYTQRWMVXFSVZLOTLFWIMLIYGNFDDESWMXUVHNQVJZGKPDZFJMCJCMSAASKEXTLSJRGGTYCGCQFPOQOMROUHJKNTQRYHJIFCXBYWHFUTFZMJCDLIVNUXMRDFGHKQLQZAEEAZKOOMVPYSJWNCYQYABUTSITEZURQHBUWABEXRCUIWAFNFVCASMRMBQNUPRUSKHSMEICAQQESYYVOPEPMVDOSIBRVQOGHDEIKBPQBFGRUFXDSQCHJKUXPXNGEBXRMQDGQJSOSENCRBWKNLLVUCVUBYOZFMTTXTLSRRNRQAVSHASZRENHLBZPNPJGQFTVWGUKJWSEKFCGLLBZLNVMOSMVQUBTWSGLTVMMZMSLQDXQIIFZKAQHSXZGUSEUEXLYCGUBHDNWHRSSYIYBITCOOWLHMMRDPGTRDWALVFFKNWIBHWHACQFJCMWUPOXONAVVXWSVPRPYMSKZNABSQUWSSUCXRMYWERFPZIQDZIYCNYNTHGMDAVYBZBQGCRGVWALCPTUTZXSQLKCHITHCDEZSAEFLDDFLGTIXBNAGKQRZESCKRIHWQPLFPSPKQVIFBMNQWIDBKZQIYGWFUNEFIWPXUEUMDWUGBFGNOJJRJPAFGKIRRUEOEZABCQLZMCDWMKLVYZVUUGHETWKXZUZFVOIRAREYBLWPRDNETKYIGXYQPZXECKYGYXTHSZXGYIDGLDNLZEQNDBVACJQYHFKQVLIUSQMXYEQYQORZMMJWSUICNYXQNKTYLAQNVBJLLTEXGRHIFDNEUGYSZNCRWGIDCFJGDZKOQFQBWEUCHTDVPIUNKPEHCSHTMRENTGSNDNBTBBBMOGUOPYPWKAPKRWISAMNXAGZFQSFSXTYXEPBPUMTLUJGXUENMZGGJMGIUTQOELTNLYBOQJEGCVUUIILMSBNALVBSFUTYARENKEWZLPWGQZFNNKEXXDSUFCJVRBKESROBOSUZUMUCCGMRSMXZTPSIQCHFCLVZKMVFMUSCNBRLCZVFZWMMKTUSAJDHOCMPRJLNDYDXROJJAHOCITARXLQXQJXQHPFZEWPYYKZEQJPEHSGIQVYEZBQWNPYUCIOBBLOXJXUOZSUVQWPHLHGLUFBHJGBPKXJXIYEUWMDUSFMLXKVQSMWYTKJOAKBNPGPHEFWPQNRBXWDAIPPUEOLNGEDDTRXPAXXZIWPHXKEINQSDIVGPLBCSZJHSXEICKSXBSEJHGMKIHTJCQQWXFTJSWWPTMGZPTQNOIXWPARKLAYJDSBIJTRGTXGZFCPUCHDSMKVQRHGDIIDNNUNWSXSCQQNNQMPCPKAGZSXMCBORWJYQNNOUSXHSDMKLMNDNTFUKMSKHNFJNFRVHQOMOFZKQIPTSIHALUJJXKBURWSBDLLAPWRQCARXMLZQWFCALVWXJFLFJTSTVRCTLBKBSJPNXYHSCXDXEPBWQECEWRZCITMDFBWZHIOWCPGGBUNWIOPNJDJCWRMIXZQULDIALDWRDJMBHVKGQYSPROVNZFRBAJESSMYBYCKDQMSXSRYDSKOIKTYFXJOMBTWYSKCTDJFQUVEKMCKRWIVZAYCTXCFXPTGRUPRMPNZSWUOEGWGDBBYPIRUISJQIBACPBOMBSJOQOZZSDGDRGYQYRFRKSSNTFGUDFQRQZXECGUCLNXEATMLQXSJKYJXIFIRSWZUDOLGMNLJJZJUJUAYJIICCERVHAVVGTCWHLSRWAQOTOGOKHTWGJMFQSLVHZPGNSFQHGBOEHMZPONRTKQJUANPNUFNLUEZLJSQVTOFMENWFZLGRRPZETXOGOBQRHUHLYGENSBKPWQBWWCZNXEIYOZTKMGCVJUSURKTIEHAHZRNTRRASIKBABWCSRHACZNXYUDGFPQDPGUIJAWGHWVVFOGDVTUHMORJCEOFCTZQYGFIETZKBEGKBSJZMFZRMFPMGVOAFXFYINMAYUXCJREDRYDNVLXWVHUEAIQGLUUFBBDTPVTCFHPRLYRBVVLNTRQZMQAWBSSRANJHAXTJVSXSDUOZSXLOEBLCIFBYFEUONSYRICVCCFPKZTIOALHQHQEKYDZQXMNZCAPLZLYXVFBPDZMLSFMMLGTNFRAROEBTFDUZXGPSAQWCNJIYTSRZYFWKRDLABYWHCMFGZVCYBFLKACYHHCKASMBLHBDJEOJNFWYLCQVNBMZXUGFSIYJGUICGFFIWRSZZDBZJYHVGPNFSAPUFJQFESPXBFLJGTDGSMFEECQFWFVKWEIACDITMSNFALDCQLRYCLLMCCMODLNLBKWVGMDZWAPSBZYRWXASQVCTBMTBPIDEDMVRPXQNDCAHLTGZSHJARUUEMQUXRRVTHOHCDKUURWURKWEXHYPSBUPXUISDESHLTSVFHVJVHNXGGARVCDAIIAQADYJJERNIDPERSJDQUCSAUPRZTYFOIIKLHTJSZNDDFTCGELCHWBIZHESDUXMACMZILDECEGSHHNTFZNBBBGXAVMPNFLHPXYLXKYZTWBQWPUYMQLNXMETGHTNREFEYPIVPYNDOBBRESGVLMKKXWHLOMIGIRIZLNGKYKRYMHEYWUJWMJHNZXMGMKQGSAJKYKVQJYAPLNWKCUBVBXDXYHECCRZFNEHQEIZVICXXXESNSSFEUZHSJDHXNDDSIUVXANFKPEQODIRLSYWLYMIWSHVENLIUOMFLSYIQACJUAJHXMEKLFADFVXVMPKNEDSHBYVENBOQUEAAIQWEXNQGQVJWZFOFOKVKGDGZHWRTKTDBERAPDNUMYTFOBBQCOZOJIVHKNGVYYSTJGSDFNOSJLRXPZFENFYHWAJCJUPUWYUVKORVMIUPLWVOOOJCKJMAQYSPYACDNGDNMUYRSOISLCMORSFGZZKSPOSLTNXPVOHDSPMZKEWHWNJMMMGIQUYWBYJIOWEWRVFLTAJJRDUGEJYJRKWAZOGERWKHVGTQUXUXHRUBFRQYFARBIAEPORGWQUIJHBUWQVEJGCOJNCYUOPFGFPTTEUSXAJVQYNZZISGNXJHJETOMWBTTPGBSZZUWRFNORZXTOMUWNQPKUTTCBYBDHLUVCEXTUOXMZGIBULSZOBTMXUTCCLNYXCDFHZNMWHESGEZPVXPYCZLCGYAQNKGIJUKIIIUCJFUEQBMXQNWDCYBERSZMOFRWOCLBYNJWJCRQAYJPCIJNDYZMCTZBAOQEUGWRMHPKZVYYGKOVPCKBZETSHNEBUHTMQQPMBWSEOCXEOBHDJMKFMZVMKEZWOMLYZPAGRXGXYBZTSXAEUNETDTIKJBBXUQJCDWYHWREGFYJHCTUARLQOFGWHPYUTWRBYEBFSSWBRCZWXKLTZJGMFUXARYDAGGOJPHEUSIUBPMQJMHOCQMVJOSPDFLIVMSRCQTWGXDAZUNYTIFHXQUASVCGDLOZUQZWHFSSRCXARCJFLFTMWNGZONWFSVTUKXVBSOUBBBPBFWJFNGELEKPMOADIZDSORTKABMSWCMYWNBAJXNKVMNDBVTCPNJXWMODDNTSQTHUSVZMKUEDBDKBIQDWPWPSSRJJFLSWUFMHCLMNSTZUBTCVQVQSDSSGSCJYQZWCAMNVRILESSQPZGQXDLSNCLUJDSAQBXXYLTZLQWLUORTKQADJCQUQDQCSVIXGWGLWRRMKHDSBEBBJVCGZLWTBVASPHVMNFVFJYKQMIMWXEMMAHHMZOADAPWORUVLPQOLWBMKESKAFYZBVZVMMPRWAXISDUKVMBVCVZWXYNRTKSBDZKVAGIHFGIQMSDEJXIRROWIRGQVZELYSOHWVJFALSGXGZOKBFURVECUXSIPQVRWQJOSFYBNCIHPZJCBXEFZVRIISGFIPSIAPDPBSSGLQJUHVQRXEFPTIMXEDAHMJQTVIQRXNFPOYSAKXHXRJETYDSXIXVXYUJBVQAVXZUPQPZDQLNDDXFANWNKTLPJCCPWUYGXYYXUAFYPZOAKDXPKAHVPJJFDOGNZBYIXYBMKDAQLIXEUTOJGTDJLTWPXPVUMBUDCKMDXRHQWKRRLLTGXDYOAOKDPARWBANIDRFSSIUIGZDUWZEJBBNUOIWQLSGTESHSCNVLMJYIWHAGTIMRQTFFKFGBODLWXIRGRMRDHLIHYAMXMOLBYFWCDPUTEDZCWSKRPKYKQYPKSLSQNFNDIGWHJPDQMACLGNEBLKQCEPSNLJBYOYXCHDIYYGWCHTCOXYZZBLNCVOSGCFFRHAS";
        s2 = "EVYRFZJIZJBKRFPPCJBHDPBYICJMVXNSXRLFUPSNIYCHUTSVGWTRJVNEPWFSSFOVSAXKHENZIDOHUQRMXXUMFDDYGFEYPTZCOURHVEDKDOACAMYUOYEINZLVOCYDNXYKXPFKXWMDOCFZGCBWJJZRJZVSLTCPVUHZNZLVNZBDQYJXQWGBTLLQADGXJFZRTMMVBDKCZRKYLNZEJAIPLDLCTXVKLOFQNQFFKYDZGTFBFERGKEQMCVYLZQIIUOCWGDVICASICRUGGKBSRGYLDPIWKURUYIHBERGCGGXWUJDPROKXYKQQBUITESPOHBQNKQKUGWHLHKGABAPHQTFDBFHGVBLUYXWZOPSQEUKCLFKFWKXQWZAKBWBHILYFKYOIITLSHCNCYQDMJNFJEKJJDZQSWMJVNDTMYANVUCNZBDUAMHAPYNGGABONFABHVNQMKSNYHYYDLHKFHHDKHVSUSNWPPWDWVAYFKSMBSXUJUVLAIGWMQSNZYTVWBPLYYDSPRLUCGXOAAJLLVQIIHBCKZLPASFSNSUSYFHBMCBFOSTWISFNREQQYTKDCAHMKZTUAXOJIFTFVWWVMODLJMNBJEOIFEYNMAXZKTXXPEDAGSNZUVFRKIGJDCKUZOSKNADBFLGISKPPHYGXZMCMFAZJXAZHBQBQRREZXLNIOKHFKKPDRHTCRUEQXLXXDCVJLXISJXAWESPGXOXJYGEOQZEMXIKVPQRFQBXYARJWOJEKEXBQAEDYUIPLLDVKYKWPWMZHEIQGDNKSFYMIUINMORLIZMXMLHXSJOTKROYPHYKJHRBEXHAFVCJETIIMMPQKSPSPLOAYAPWBXUJWJBBIHVGVKDCFPABBAYMCZPIMZTOQBIDTJSDNKGUDECSFSRREZFBTUXIXJKIPXTNCFQUQDTANIWLEVDYODLIIWUVBOGQZWWPURCQVRYSJZDBSUCBXNLKFGCVWAGOIQIGSNVKWEMYBVXUAERMVJJLDZSPJKPVEOFJVPVGVOKSJYIFNLGBRUBYVNDYNHVLUDRYLJRGBKYSZBXNVDBYAHLZTQXUIBBOTBFKHSGYKPGCRWLMXZMHIOVKLIJTBUTFDIOJSCLMCJTTAFLMSYDWUNHEIKKSKJHXADKTUYNCEYAJDKVEIMKSQWLOGDYYDDKSRARLPGFZZNZUAJFRFEUNQJUAWJHFMARNJUIYEUZTDVRZHCEZVSFKHCDGTTNPFNKHSYPMXBOBNQYLLOAKFKNOYPEORITDIQRKMJIFOUEIBYCUXNQNUNBTNLNKCIOHEFUQCYFOBIIYBUWPRQRFOKONIRFILFQGJHFPLYASYJMZQPDWTSBKGQCYUVTBUNQNHNAEFGPJNVAGTPXFQRGMXTSVAJTRPBDBNZQACKJDTAMMEUASETGWFZWBYSFOMABHMXHLNQTBALMJFHXROGODUKWEYZMJFHKIIATYPLTUNTSXAJRCDZFJWFXRQWHPOSVXEDXOMRDMBQHAVOCMVTKGGPULOVCCKLEYCFGTYPCNCNHTWUWRMZJPBSCIMPXCZRPBIXQVAMEGSEYPGECDDFOFLQTASXNGKYWTAIRATEGYVZXTTVBFDDKWXOBEZXFNWPZXKJCDPNLVWOZNDNFEGUHYCDOOMTXBPIQLQWOTOIBBQZWXVGKMQWOWATZOZGBTRDKNDCPIVAILDYXKKNZYIYYTBFLWPAITVIRSPTZDHLFSIABOMDXQHTFNVLEUOUKTABUAWPRTURPUMHGKBUACFFFOXRIUGZAQSENYFNMGQJMSJVOBAEUQKGWPYVWPAFBNXIODTCEMCDQAXVLNYCQYVAXXYBHVDKHIUHPVUMQBFRYWTQKUVJJEAEXYCJZHRFCLDUGQRVIYLXUBXWGTCPTVELFADQXBCVTSEZNBBUAEUDGEJCVYTASTWCZYJQHGLYHZTGDDICBNLVYTJHMHENXSDHVLYFLHZQSOIDECOJQGXSQKHVFKPLDTOQYRGADZGIOOLYNWZXJRUIIYBWCNWAUPWGMUEQDFYVSVLBHQQXKCEEBMWQJYPIYGYZCDBZPKYRHROJQMQSBJILAMAKICREORUIJZRHWWFKBVZUAJRSCCDHBKUNZVWRHSIPHMBDAMNNQKHTFKYQRDRCEXZFTUAXFRPHOIPMZXCZJCUOQCZGPRMZIOTDISOSKROXJKLEAHTIRTMVEUCAYQVJBJCRNDJVXVVOUMPCGCZWUMAAPDEPSHNAIMATOLVNLMEPBJZWXMACWILFYKKKUYSUCCMFTJUOCCUPAGBYAKRUGNNSGRVBXDWGTQZZWHBKYJDKFNIEPBOLVIWSBEVYPMEKZMEVXOHKSLYLOSUSKCVEHBYRRUYKOHNENCVYODSTLPWDWOOONHNIYEGMYEKMTOPUWYKWCTHHDWZQXOCNQGZMTCTBPUILLKMAWSSJTRGXLSPQQYCZVKHHFCUGLIDEMBZUNQFSPCPHJQTUAYWQBJJSQYDFOJZOFIRJWOIOBXHFDIMSVISXEYYRKSQALVHUQLQOJZVDMADAIUKMJQGFAFJQOYWDFXPMBUOXOEISIHWBLFTQBSBCMQSKWMHNWOJRJOOAHBBKCVKSOGUCZQZVRPBMDKQYICBHJAZSCXKVRMBXHUGUZXFEJGUIANOFATRARRNZPYVBLJZTDZLRFIZBLVXKJRFACEVLDDQMRGZYUHJFBVPVLZJFNBKPKUFNAOEJEQNSNKITMIGJVIBPVIJTBEVSSVLCQZMSHQNDHZHXGGCTDZPOGABKKJTNIXVVGXHKRYILLFJMJHNKKDIQWGPPVJVCBWEQOVKIDJEBMKOLZGSLHMRHIQGLVWHICMTOXPITUPETITSOBUIWYRKMZMWFMSGFOORVZEBZPVBSCVETJKNHNQTJHNINAVAWQHPKHBCSAGDNLYAADUSPPTMGECGCBYDSMNGFPLHLMEQPDUYJVIRVRZFBRFMQKEWXOTZGTHMVBXBNUYACTBIRPPWWYMERCDUZTLKJJMXGBHFINZEEZPOFNCRTDYCEPYBZWNHKZXKNJNOTDVKFKXWKWOBTKAWEBEZAJMLPFUXOAQMEEAPDEWVJDVHMJIFZVPJKAZWPCVLRUWWXATRFIEKSQFVFIGJWYTMBMLLCQPQJNELFQLBCGLODVHXBWJNTQPKEMPRBWNAVCPJWKCIEKOOVBCMSLERUKVJCQAIXQBCHUXCDIQCSOTCHLQBYAXHWAJAFWZAQMHRTXMFDIWFFYQTVVSWXGIDAOHUHDQSYMOZESUHIEHBBCHTPTSBKBWVRSVNDQYSXMWNDBAECMHPOPMSAUCXOHITLWDNCPBVMKCUDAJESJBGMVXVTPBOOPAEGUGLKUJIIZPQPQVYPIBOOOBDOJUFCVEWMZMADYLIHDJJYALDZXDYNFCKNHQBIJHMAJWMYIYKCNGPRIVZMOUZRXRFVMZSDHAQJEOGJYEJIWHZSEHQVUBFNEFKFLTAFNFCDXNMJIXQNOPGMYVZUVWUFVKFXMYCBBQIUBWEVLYJHDSMZEDRJWVLPVCXJLPNACMEHOWHRJIEEXEAVAHUQYRYHEGRGDBHSGQAHFIIHVPSVXGQJRGOBNTLFILNFPMIDKEHUQBJHCKHBCWEWFAIILHINFVDOJBDLPGNPRTORKRMMCZNCYWFYDRTUYOTFRMZVPHRKKTERJKIJUWCVJANXEQWCUIXLWFUEGNPHHKELAKDLTQEEBMUUQHEJPOBTDLYRFBZGUOKCWARIBQJCUUBQLYRUMVCRGTEIYUDJIDIPZNNGMPNFIONDGFQGPEQPGEKNHEJMNTASLYRZUYKRIIERZLIDIHPEBYIBUDVYNXGVVKLOQPDVRNYLINDKNAEHTDIOLSUFZGOXGOFOFURIVLOCFBPGCYOCVBGFMKALQZNBKWNZPKIQWRLIMNKBBXJPQHCTUXAHUQTFBRBKDAFALDVVZRRPLOEVUCBUDBUUUFWFGBUWGKAKDRYCBVLOSSJZHSCYIXWOYBDUWSBFKSHSKVZLBNDNHNCZSKZNQIUUUYNVRDUPFJORXZDEVVRNGHYBKQEANYEYJDCQXPHEKNPAKRGZTODMNJKQZAICVLWCIWYDFSRDDBGVFKCOCZAZKDUBFTKLNYPTMRKJIOKPECEIBEABQGCKLJYMBIACXYPKNGHOKWBIPGLAXBVXKWCJCDGHCKWAZBRDFRNTZBZVCTXKTQNXTNGQWVAPWVLZXPXCSIOJWHWTZYXLZUSVTRYNJXQZBBVZXFBHJCDAWJAUJUUGLNTLCNIGZINJUAZHHOZCTXPCRNSMQBEEZWHIBSEAFALDETCFKCVLMQCCLZIVVZUMHGAEPAIFGSCINZQJEDNTSYGVTWSQWREKGFEUSLRZYXXTXABLAWYWMPYFXBIGRRITWTELCILVQRNWLJMNBSCILFZOUJGRGQWZDGXZWSVKVWQTMUKXZDCYDENZNEIMHXWYATJEMDEQTXYPMVFEKHSPZFXHYKPGWLSJCDOBYFGEUGOVJNXOTXKDHJTIFFTJWHHDXCNWOLPHGOHJORXKQUCTLSDMGSVRDUPWVZRUSEDIOMVFSYERIWIXVPSMHIISMNSWPVAQEIXIRJNABCAPOHKXTFXZANQJUSTEAOCTNXACBOTRLXHGLSXMHMATZXFLTFJDAIYSQSZEJUPSBRGFEGHBWIJAVFINBBCFQRPUTGARLCNJYHZIADZPSUBZPEYPEFLUGWPGIFNXTGBJCGRLVVBMDKLGNYPLZBXZQSSQYLZZTKJLNMXMSLBOCOXQPGDARJAFTDZCVJSPXXIZQIEHGYOBWULEYAZGISEMUWVNCIPBDJCLIWBCPKZKPUQFTITGLWJTLHALWOUYGHRGJWRCNNRSAELAKVVUXUGYMUKRJBYFKYENHZBDTQEBVLTGLCJSEJWJUZCYNCMRIUQNNCNIAGCTKLFEZDFWKHLPIWZCZGHYCJLJQWVKMDNBJNKIZWBWHQWPPJNREFYQMCDUGXMDDRUAYHZUNFGCKDDZSJAKIEYZCZRHEFNJXLISNBITZYWZEDQNQCPAXPTG";
//        resolve(s1, s2, 1618);

    }

    private static void resolve(String s1, String s2, int e) {
        long alpha = System.currentTimeMillis();
        int  a     = commonChild(s1, s2);
        long omega = System.currentTimeMillis();
        System.out.println(omega - alpha + "ms");
        var b = e == a;
        System.out.println(b);
        if (!b) {
            System.out.println(a);
            System.out.println(e);
        }
    }

    public static int commonChild(String s1, String s2) {

        System.out.println();

        System.out.println(result);

        System.out.println();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println();

        var a = common(s1, s2);
        var b = common(s2, s1);

        log.debug(a);
        log.debug(b);

        int len = a.size();

        if (a.equals(b)) {
            return len;
        }

        for (int i = 0; i < len; i++) {

        }

        s1 = String.join("", a);
        s2 = String.join("", b);

        for (int i = 0; i < len - 2; i++) {

            String s = s1.substring(i, i + 2);
            if (s2.contains(s)) {

                var chunks1 = s1.split(s);
                var chunks2 = s2.split(s);

                result += commonChild(chunks1, chunks2, 0);
                result += commonChild(chunks1, chunks2, 1);
                result += 2;
                continue;
            }

            s = s2.substring(i, i + 2);

            if (s1.contains(s)) {

                var chunks1 = s1.split(s);
                var chunks2 = s2.split(s);

                result += commonChild(chunks1, chunks2, 0);
                result += commonChild(chunks1, chunks2, 1);
                result += 2;
            }
        }

        return 0;
    }

    public static int commonChild(String[] a1, String[] a2, int i) {

        log.debug(Arrays.toString(a1));
        log.debug(Arrays.toString(a2));

        String s1 = String.join("", a1);
        String s2 = String.join("", a2);

        var a = common(a1, s2);
        var b = common(a2, s1);

        int len = a.size();

        if (a.equals(b)) {
            return len;
        }

        if (len > 2) {
            return commonChild(String.join("", a), String.join("", b));
        }

        System.out.println(s1);
        System.out.println(s2);

        return 0;

    }

    public static List<String> common(String a, String b) {
        return Stream
                .of(a.split(""))
                .filter(b::contains)
                .collect(Collectors.toList());
    }

    public static List<String> common(String[] a, String b) {
        return Stream
                .of(a)
                .filter(b::contains)
                .collect(Collectors.toList());
    }

}

