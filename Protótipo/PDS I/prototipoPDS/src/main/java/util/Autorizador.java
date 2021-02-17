package util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import model.Usuario;

public class Autorizador implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent evento) {
        FacesContext context = evento.getFacesContext();
        String nomePagina = context.getViewRoot().getViewId();
        if(nomePagina.equals("/view/login.xhtml")) {
            return;
        }
        Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
        if(usuarioLogado != null) {
            return;
        }
        NavigationHandler handler = context.getApplication().getNavigationHandler();
        handler.handleNavigation(context, null, "/view/login?faces-redirect=true");
        context.renderResponse();
    }

    @Override
    public void beforePhase(PhaseEvent pe) {}

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}