import NavLeft from "../../components/common/NavLeft";
import NavTop from "../../components/common/NavTop";
import WebhookFrame from "../../components/manage/WebhookFrame";

export default function WebhookPage(){
    return<>
    <NavTop/>
    <NavLeft num={1}/>
    <WebhookFrame/>
    </>
}