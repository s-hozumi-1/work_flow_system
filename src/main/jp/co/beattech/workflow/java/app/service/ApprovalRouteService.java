package jp.co.beattech.workflow.java.app.service;
import java.util.List;
import org.springframework.stereotype.Service;

import jp.co.beattech.workflow.java.app.domain.ApprovalRoute;

@Service
public interface ApprovalRouteService {
    List<ApprovalRoute> getAllapprovalRoutes();
}
