import com.gh0u1l5.wechatmagician.util.MessageUtil
import com.gh0u1l5.wechatmagician.util.Version
import org.junit.Test as Test
import org.junit.Assert.*

class UnitTest {
    @Test fun CustomizeTest() {
        assertNull(MessageUtil.customize(null, ""))
        assertEquals("test 妄图撤回一条消息，啧啧", MessageUtil.customize("\"test\" 撤回了一条消息", "妄图撤回一条消息，啧啧"))
    }

    @Test fun NotifyChatroomMsgRecallTest() {
        assertEquals("wxid:\ntest", MessageUtil.notifyChatroomMsgRecall("", "wxid:\ntest"))
        assertEquals("wxid:\n[已撤回] test", MessageUtil.notifyChatroomMsgRecall("[已撤回]", "wxid:\ntest"))
        assertEquals("wxid:\n[已撤回] test", MessageUtil.notifyChatroomMsgRecall("[已撤回]", "wxid:\n[已撤回] test"))
    }

    @Test fun NotifyPrivateMsgRecallTest() {
        assertEquals("test", MessageUtil.notifyPrivateMsgRecall("", "test"))
        assertEquals("[已撤回] test", MessageUtil.notifyPrivateMsgRecall("[已撤回]", "test"))
        assertEquals("[已撤回] test", MessageUtil.notifyPrivateMsgRecall("[已撤回]", "[已撤回] test"))
    }

    @Test fun NotifyLinkRecallTest() {
        assertEquals("<msg><title>test</title></msg>", MessageUtil.notifyLinkRecall("", "<msg><title>test</title></msg>"))
        assertEquals("<msg><title>[已撤回] test</title></msg>", MessageUtil.notifyLinkRecall("[已撤回]", "<msg><title>test</title></msg>"))
        assertEquals("<msg><title>[已撤回] test</title></msg>", MessageUtil.notifyLinkRecall("[已撤回]", "<msg><title>[已撤回] test</title></msg>"))
    }

    @Test fun NotifyInfoDeleteTest() {
        val msg = MessageUtil.hexStringToBytes("0A143132343738373630313034393237313730373830120B7171323737383233323038180020FEF6AAC5052A47E5A881E5B091EFBC9AE78EB0E59CA8E585A8E6988EE6989F6D7670E8BF98E69C89E5A596E59381E38082E38082E4BDA0746DE4B88DE697A9E8AFB4EFBC81EFBC81EFBC81EFBC81321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A0042080A0010021A0022004A0052005A006000680372007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100")
        val expect = "0A143132343738373630313034393237313730373830120B7171323737383233323038180020FEF6AAC5052A535BE5B7B2E588A0E999A45D20E5A881E5B091EFBC9AE78EB0E59CA8E585A8E6988EE6989F6D7670E8BF98E69C89E5A596E59381E38082E38082E4BDA0746DE4B88DE697A9E8AFB4EFBC81EFBC81EFBC81EFBC81321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A0042080A0010021A0022004A0052005A006000680372007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100"
        assertEquals(expect, MessageUtil.bytesToHexString(MessageUtil.notifyInfoDelete("[已删除]", msg)))
    }

    @Test fun NotifyLongInfoDeleteTest() {
        val msg1 = MessageUtil.hexStringToBytes("0A1431323535323535353130393934393634343936311213777869645F6D7175306A7A377773747A333232180020F8EDC3C9052ABC017465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A00420A0A0010021A00220030004A0052005A006000680072007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100")
        val expect1 = "0A1431323535323535353130393934393634343936311213777869645F6D7175306A7A377773747A333232180020F8EDC3C9052AC8015BE5B7B2E588A0E999A45D207465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A00420A0A0010021A00220030004A0052005A006000680072007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100"
        assertEquals(expect1, MessageUtil.bytesToHexString(MessageUtil.notifyInfoDelete("[已删除]", msg1)))
        val msg2 = MessageUtil.hexStringToBytes("0A1431323535373033373138323238363530343131301213777869645F6D7175306A7A377773747A33323218002099BCE4C9052A800274657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A00420A0A0010021A00220030004A0052005A006001680272007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100")
        val expect2 = "0A1431323535373033373138323238363530343131301213777869645F6D7175306A7A377773747A33323218002099BCE4C9052A8C025BE5B7B2E588A0E999A45D2074657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A00420A0A0010021A00220030004A0052005A006001680272007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100"
        assertEquals(expect2, MessageUtil.bytesToHexString(MessageUtil.notifyInfoDelete("[已删除]", msg2)))
    }

    @Test fun NotifyReallyLongInfoDeleteTest() {
        val msg = MessageUtil.hexStringToBytes("0A1431323535363937363837343930303735383731351213777869645F6D7175306A7A377773747A3332321800208484E4C9052AC009746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A00420A0A0010021A00220030004A0052005A006001680272007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100")
        val expect = "0A1431323535363937363837343930303735383731351213777869645F6D7175306A7A377773747A3332321800208484E4C9052ACC095BE5B7B2E588A0E999A45D20746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374746573747465737474657374321F0D0000000015000000001A0022002A003200380048005000580065000000003A0A0A0012001A0022002A00420A0A0010021A00220030004A0052005A006001680272007A2408001200180022002A0032003A080A0012001A00220042004A040800100052040A0012008001008A01100A0010001A0022002A0032003A0042009201009A0100A201040A001200A80100"
        assertEquals(expect, MessageUtil.bytesToHexString(MessageUtil.notifyInfoDelete("[已删除]", msg)))
    }

    @Test fun NotifyCommentDeleteTest() {
        val msg = MessageUtil.hexStringToBytes("0A12777869645F393534313434353431353731311213777869645F63707874653974717562336F32321A0CE789A7E4BA91E5A4A9E699AF2209E69E97E4BFAEE4B9902802301E38A5C8ADC50542365BE598BFE593885D5BE68D82E884B85D5BE5A5B8E7AC915D5BE69CBAE699BA5D5BE79AB1E79C895D5BE880B65DE59190E59190E59190480050C10158016000680072020800")
        val expect = "0A12777869645F393534313434353431353731311213777869645F63707874653974717562336F32321A0CE789A7E4BA91E5A4A9E699AF2209E69E97E4BFAEE4B9902802301E38A5C8ADC50542425BE5B7B2E588A0E999A45D205BE598BFE593885D5BE68D82E884B85D5BE5A5B8E7AC915D5BE69CBAE699BA5D5BE79AB1E79C895D5BE880B65DE59190E59190E59190480050C10158016000680072020800"
        assertEquals(expect, MessageUtil.bytesToHexString(MessageUtil.notifyCommentDelete("[已删除]", msg)))
    }

    @Test fun SnsMsgTest() {
        assertArrayEquals(MessageUtil.hexStringToBytes("03"), MessageUtil.encodeMsgSize(0x03))
        assertArrayEquals(MessageUtil.hexStringToBytes("7F"), MessageUtil.encodeMsgSize(0x7F))
        assertArrayEquals(MessageUtil.hexStringToBytes("8001"), MessageUtil.encodeMsgSize(0x80))
        assertArrayEquals(MessageUtil.hexStringToBytes("8401"), MessageUtil.encodeMsgSize(0x84))
        assertArrayEquals(MessageUtil.hexStringToBytes("8002"), MessageUtil.encodeMsgSize(0x100))
        assertArrayEquals(MessageUtil.hexStringToBytes("8004"), MessageUtil.encodeMsgSize(0x200))
        assertArrayEquals(MessageUtil.hexStringToBytes("F803"), MessageUtil.encodeMsgSize(0x1F8))
        assertEquals(Pair(1, 3), MessageUtil.decodeMsgSize(0, MessageUtil.hexStringToBytes("03FF")))
        assertEquals(Pair(2, 128), MessageUtil.decodeMsgSize(0, MessageUtil.hexStringToBytes("8001")))
        assertEquals(Pair(2, 132), MessageUtil.decodeMsgSize(0, MessageUtil.hexStringToBytes("8401")))
        assertEquals(Pair(2, 384), MessageUtil.decodeMsgSize(0, MessageUtil.hexStringToBytes("8003")))
        assertEquals(Pair(2, 504), MessageUtil.decodeMsgSize(0, MessageUtil.hexStringToBytes("F803")))
        assertEquals(Pair(2, 512), MessageUtil.decodeMsgSize(0, MessageUtil.hexStringToBytes("8004")))
    }

    @Test fun BytesToHexStringTest() {
        assertEquals("", MessageUtil.bytesToHexString(null))
        assertEquals("", MessageUtil.bytesToHexString(byteArrayOf()))
        assertEquals("0102030405060708090A0B0C0D0E0F", MessageUtil.bytesToHexString(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F)))
        assertEquals("F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF", MessageUtil.bytesToHexString(byteArrayOf(-0x0F, -0x0E, -0x0D, -0x0C, -0x0B, -0x0A, -0x09, -0x08, -0x07, -0x06, -0x05, -0x04, -0x03, -0x02, -0x01)))
    }

    @Test fun HexStringToBytesTest() {
        assertArrayEquals(byteArrayOf(), MessageUtil.hexStringToBytes(null))
        assertArrayEquals(byteArrayOf(), MessageUtil.hexStringToBytes(""))
        assertArrayEquals(byteArrayOf(0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F), MessageUtil.hexStringToBytes("0102030405060708090A0B0C0D0E0F"))
        assertArrayEquals(byteArrayOf(-0x0F, -0x0E, -0x0D, -0x0C, -0x0B, -0x0A, -0x09, -0x08, -0x07, -0x06, -0x05, -0x04, -0x03, -0x02, -0x01), MessageUtil.hexStringToBytes("F1F2F3F4F5F6F7F8F9FAFBFCFDFEFF"))
    }

    @Test fun VersionCompareTest() {
        assertTrue(Version("1") < Version("2"))
        assertTrue(Version("1") == Version("1"))
        assertTrue(Version("1.2") < Version("1.2.1"))
        assertTrue(Version("1.12") > Version("1.1"))

        assertTrue(Version("6.5.8") > Version("6.5.4"))
        assertTrue(Version("6.5.8") >= Version("6.5.4"))
        assertTrue(Version("6.5.4") >= Version("6.5.4"))
        assertTrue(Version("6.5.4") == Version("6.5.4"))
    }
}