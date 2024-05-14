import { useState } from "react";
import NavTop from "../components/common/NavTop";
import NavLeft from "../components/common/NavLeft";
import LogMadal from "../components/modal/LogModal";
import { getLog } from "../api/Docker";
import useModalStore from "../stores/modalStore";


export default function TestPage() {

    const [log, setLog] = useState("");
    const { modalOpen, setModalOpen } = useModalStore();


    const logString = `
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\
( ( )\\___ | '_ | '_| | '_ \\/ _\` | \\ \\ \\ \\
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.5)

2024-05-14T10:02:12.157+09:00  INFO 3996 --- [backend] [           main] c.d.b.BackendApplication                 : Starting BackendApplication using Java 17.0.9 with PID 3996 (C:\\Users\\SSAFY\\Desktop\\도비\\S10P31B101\\backend\\build\\classes\\java\\main started by SSAFY in C:\\Users\\SSAFY\\Desktop\\도비\\S10P31B101\\backend)
2024-05-14T10:02:12.162+09:00  INFO 3996 --- [backend] [           main] c.d.b.BackendApplication                 : The following 2 profiles are active: "filter", "jwt"
2024-05-14T10:02:13.097+09:00  INFO 3996 --- [backend] [           main] o.s.b.w.e.t.TomcatWebServer              : Tomcat initialized with port 8080 (http)
2024-05-14T10:02:13.106+09:00  INFO 3996 --- [backend] [           main] o.a.c.c.StandardService                  : Starting service [Tomcat]
2024-05-14T10:02:13.106+09:00  INFO 3996 --- [backend] [           main] o.a.c.c.StandardEngine                   : Starting Servlet engine: [Apache Tomcat/10.1.20]
2024-05-14T10:02:13.146+09:00  INFO 3996 --- [backend] [           main] o.a.c.c.C.[.[.[/]                        : Initializing Spring embedded WebApplicationContext
2024-05-14T10:02:13.146+09:00  INFO 3996 --- [backend] [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 955 ms
2024-05-14T10:02:13.219+09:00  INFO 3996 --- [backend] [           main] c.d.b.u.CookieUtil                       : message: CookieUtil is configured
Key length in bytes: 33
2024-05-14T10:02:13.920+09:00  INFO 3996 --- [backend] [           main] o.s.b.a.e.w.EndpointLinksResolver        : Exposing 1 endpoint(s) beneath base path '/actuator'
2024-05-14T10:02:13.944+09:00  INFO 3996 --- [backend] [           main] o.s.s.w.DefaultSecurityFilterChain       : Will secure any request with [org.springframework.security.web.session.DisableEncodeUrlFilter@391d28ea, org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@23df7fad, org.springframework.security.web.context.SecurityContextHolderFilter@7cdc4070, org.springframework.security.web.header.HeaderWriterFilter@11c3d22f, org.springframework.web.filter.CorsFilter@4ab455e2, org.springframework.security.web.authentication.logout.LogoutFilter@4fea5ee0, com.dobie.backend.security.filter.RefreshTokenRequestFilter@9263c54, com.dobie.backend.security.filter.JwtAuthenticationFilter@15369d73, com.dobie.backend.security.filter.TokenExceptionFilter@693f2213, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@6abe62bb, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@1f60824e, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@2acca224, org.springframework.security.web.session.SessionManagementFilter@d949bc4, org.springframework.security.web.access.ExceptionTranslationFilter@3fef1e6b, org.springframework.security.web.access.intercept.AuthorizationFilter@459bf87c]
2024-05-14T10:02:14.139+09:00  INFO 3996 --- [backend] [           main] o.s.b.w.e.t.TomcatWebServer              : Tomcat started on port 8080 (http) with context path ''
2024-05-14T10:02:14.150+09:00  INFO 3996 --- [backend] [           main] c.d.b.BackendApplication                 : Started BackendApplication in 2.336 seconds (process running for 2.811)
2024-05-14T10:02:14.550+09:00  INFO 3996 --- [backend] [-192.168.31.244] o.a.c.c.C.[.[.[/]                        : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-05-14T10:02:14.550+09:00  INFO 3996 --- [backend] [-192.168.31.244] o.s.w.s.DispatcherServlet                : Initializing Servlet 'dispatcherServlet'
2024-05-14T10:02:14.552+09:00  INFO 3996 --- [backend] [-192.168.31.244] o.s.w.s.DispatcherServlet                : Completed initialization in 1 ms
`;


    const handleLogModal = async () => {
        setModalOpen(true);
            setLog(logString);
        
      };
    return (
        <>
            {/* <NavTop /> */}
            {/* <NavLeft /> */}
            <div onClick={() =>
                handleLogModal()
              }>버튼</div>
            {modalOpen && <LogMadal content={log}/>}
        </>
    );
}
